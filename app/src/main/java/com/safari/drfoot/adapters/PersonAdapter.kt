package com.safari.drfoot.adapters

import android.app.Activity
import android.os.Bundle
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
import com.safari.drfoot.activities.ScenarioActivity
import com.safari.drfoot.entities.Person

const val PERSON_ID_KEY = "personId"

class PersonAdapter(private val context: Activity, private val mDataset: List<Person>)
    : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root: View = v.findViewById(R.id.root)
        val title: TextView = v.findViewById(R.id.textView)
        val image: ImageView = v.findViewById(R.id.imageView)
        val lock: ImageView = v.findViewById(R.id.lockImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_person, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = mDataset[position]

        holder.title.text = person.name

        if (!TextUtils.isEmpty(person.imageUrl)) {
            Glide.with(context).load(person.imageUrl).into(holder.image)
        }  else {
            Glide.with(context).load(person.imageLocal).into(holder.image)
        }

        if (person.isLocked) {
            holder.lock.visibility = View.VISIBLE
            holder.image.alpha = .3f
            holder.root.setOnClickListener {
                YoYo.with(Techniques.Wobble).playOn(holder.lock)
            }
        }
        else {
            holder.lock.visibility = View.INVISIBLE
            holder.image.alpha = 1f
            holder.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt(PERSON_ID_KEY, person.id)
                Navigator.withBundle(bundle).changeActivityFade(context, ScenarioActivity::class.java, false)
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}
