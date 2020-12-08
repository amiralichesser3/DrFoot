package com.safari.drfoot.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.safari.drfoot.R
import com.safari.drfoot.entities.Answer
import com.safari.drfoot.utilities.contracts.MyCallback

class AnswerAdapter(private val mDataset: List<Answer>, private val callback: MyCallback<Answer>, private val callback2: MyCallback<Boolean>)
    : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {

    private val disabledIds: ArrayList<Int> = ArrayList()

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root: View = v.findViewById(R.id.root)
        val title: TextView = v.findViewById(R.id.textView)
        val tick: ImageView = v.findViewById(R.id.tick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_answer, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subsection = mDataset[position]
        holder.title.text = subsection.text
        holder.root.setOnClickListener {
            if (disabledIds.contains(subsection.id)) {
                return@setOnClickListener
            }
            disabledIds.add(subsection.id)

            if (subsection.isCorrect) {
                YoYo.with(Techniques.BounceIn).playOn(it)
                holder.title.setTextColor(Color.DKGRAY)
                holder.tick.visibility = View.VISIBLE
                callback.onSuccess(subsection)

                var flag = true

                mDataset.forEach { element ->
                    if (!disabledIds.contains(element.id)) {
                        if (flag) {
                            flag = !element.isCorrect
                        }
                    }
                }

                if (flag) {
                    callback2.onSuccess(flag)
                }

            } else {
                YoYo.with(Techniques.Hinge).playOn(it)
                callback.onError(subsection)
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}
