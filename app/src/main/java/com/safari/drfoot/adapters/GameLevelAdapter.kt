package com.safari.drfoot.adapters

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.hafezie.barname.utility.Navigator
import com.safari.drfoot.R
import com.safari.drfoot.activities.GameLevelActivity
import com.safari.drfoot.entities.GameLevel

const val GAMELEVEL_ID_KEY = "gameLevelId"
const val CATEGORY_KEY = "categoryKey"

class GameLevelAdapter(private val context: Activity, private val mDataset: List<GameLevel>)
    : RecyclerView.Adapter<GameLevelAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root: View = v.findViewById(R.id.root)
        val title: TextView = v.findViewById(R.id.textView)
        val lock: ImageView = v.findViewById(R.id.lockImage)
        val image: ImageView = v.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameLevelAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_gamelevels, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = mDataset[position]

        holder.title.text = content.name

        if (!TextUtils.isEmpty(content.image)) {
            Glide.with(context).load(content.image).into(holder.image)
        }

        if (content.isLocked) {
            holder.lock.visibility = View.VISIBLE
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.grey))

        } else {
            holder.lock.visibility = View.GONE
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }

        holder.root.setOnClickListener {
            if (content.isLocked) {
                YoYo.with(Techniques.Tada).playOn(holder.lock)
                return@setOnClickListener
            }
            val bundle = Bundle()
            bundle.putInt(GAMELEVEL_ID_KEY, content.id)
            bundle.putString(CATEGORY_KEY, content.name)
            Navigator.withBundle(bundle).changeActivityFade(context, GameLevelActivity::class.java, false)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}
