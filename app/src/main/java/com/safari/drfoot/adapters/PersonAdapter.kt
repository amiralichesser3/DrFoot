package com.safari.drfoot.adapters

import android.app.Activity
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
import com.safari.drfoot.entities.CoinPerSectionPerPerson
import com.safari.drfoot.entities.Person
import com.safari.drfoot.utilities.contracts.MyCallback

const val PERSON_ID_KEY = "personId"

class PersonAdapter(private val context: Activity, private val mDataset: List<Person>,
                    private val mDataset2: List<CoinPerSectionPerPerson>,
                    private val callback: MyCallback<Int>)
    : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root: View = v.findViewById(R.id.root)
        val title: TextView = v.findViewById(R.id.textView)
        val image: ImageView = v.findViewById(R.id.imageView)
        val lock: ImageView = v.findViewById(R.id.lockImage)
        val coinFrame: View = v.findViewById(R.id.coinFrame)
        val coinTextView: TextView = v.findViewById(R.id.coinTextView)
        val timerFrame: View = v.findViewById(R.id.timerFrame)
        val timerTextView: TextView = v.findViewById(R.id.timerTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_person, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = mDataset[position]
        var coinCount: Int? = null
        var timerText: String? = null

        mDataset2.forEach {
            if (it.personId == person.id) {
                coinCount = it.coinCount
                timerText = it.timer
            }
        }

        holder.coinFrame.visibility = View.INVISIBLE
        holder.timerFrame.visibility = View.INVISIBLE

        coinCount?.let {
            if (it == 0) return@let
            holder.coinFrame.visibility = View.VISIBLE
            val text = " X $it"
            holder.coinTextView.text = text
        }

        timerText?.let {
            holder.timerFrame.visibility = View.VISIBLE
            holder.timerTextView.text = it
        }

        holder.title.text = person.name

        if (!TextUtils.isEmpty(person.imageUrl)) {
            Glide.with(context).load(person.imageUrl).into(holder.image)
        }  else {
            person.imageLocal?.let {
                holder.image.setImageResource(it)
            }
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
                callback.onSuccess(person.id);
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}
