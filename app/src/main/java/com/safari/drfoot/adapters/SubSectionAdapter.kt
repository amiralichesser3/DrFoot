package com.safari.drfoot.adapters

import android.content.Context
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
import com.safari.drfoot.R
import com.safari.drfoot.entities.SubSection
import com.safari.drfoot.utilities.contracts.MyCallback

class SubSectionAdapter( private val context: Context?, private val mDataset: List<SubSection>, private val callback: MyCallback<SubSection>)
    : RecyclerView.Adapter<SubSectionAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root: View = v.findViewById(R.id.root)
        val title: TextView = v.findViewById(R.id.textView)
        val lock: ImageView = v.findViewById(R.id.lockImage)
        val image: ImageView = v.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubSectionAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_gamelevels, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subsection = mDataset[position]

        holder.title.text = subsection.name

        if (!TextUtils.isEmpty(subsection.image)) {
            Glide.with(context!!).load(subsection.image).into(holder.image)
        }

        holder.root.setOnClickListener {
            if (subsection.isLocked) {
                YoYo.with(Techniques.Tada).playOn(holder.lock)
                return@setOnClickListener
            }

            callback.onSuccess(subsection)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}
