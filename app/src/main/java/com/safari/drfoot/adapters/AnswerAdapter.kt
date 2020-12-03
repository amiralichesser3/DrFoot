package com.safari.drfoot.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.safari.drfoot.R
import com.safari.drfoot.entities.Answer
import com.safari.drfoot.utilities.contracts.MyCallback

class AnswerAdapter(private val mDataset: List<Answer>, private val callback: MyCallback<Answer>)
    : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root: View = v.findViewById(R.id.root)
        val title: TextView = v.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_subsection, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subsection = mDataset[position]
        holder.title.text = subsection.text
        holder.root.setOnClickListener {
            callback.onSuccess(subsection)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}
