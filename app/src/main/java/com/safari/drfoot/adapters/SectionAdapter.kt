package com.safari.drfoot.adapters

import android.app.Activity
import android.content.Context
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
import com.safari.drfoot.utility.Navigator
import com.safari.drfoot.R
import com.safari.drfoot.activities.GameLevelActivity
import com.safari.drfoot.activities.LeafSectionActivity
import com.safari.drfoot.entities.GameLevel
import com.safari.drfoot.entities.Section
import com.safari.drfoot.utilities.contracts.MyCallback
import retrofit2.Response


class SectionAdapter(private val context: Context, private val mDataset: List<Section>, private val callback: MyCallback<Int>)
    : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root: View = v.findViewById(R.id.root)
        val title: TextView = v.findViewById(R.id.textView)
        val lock: ImageView = v.findViewById(R.id.lockImage)
        val image: ImageView = v.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_gamelevels, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = mDataset[position]

        holder.title.text = section.name

        if (!TextUtils.isEmpty(section.image)) {
            Glide.with(context).load(section.image).into(holder.image)
        }

        if (section.isLocked) {
            holder.lock.visibility = View.VISIBLE
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.grey))

        } else {
            holder.lock.visibility = View.GONE
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }

        holder.root.setOnClickListener {
            if (section.isLocked) {
                YoYo.with(Techniques.Tada).playOn(holder.lock)
                return@setOnClickListener
            }

            callback.onSuccess(section.id)


        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}
