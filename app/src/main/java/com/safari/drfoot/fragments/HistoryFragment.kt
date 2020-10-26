package com.safari.drfoot.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ablanco.zoomy.Zoomy
import com.bumptech.glide.Glide
import com.safari.drfoot.utility.InjectorFragment

import com.safari.drfoot.R
import com.safari.drfoot.entities.History
import com.safari.drfoot.viewmodels.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*

private const val PERSON_ID = "pid"
class HistoryFragment : InjectorFragment<HistoryViewModel>() {
    private var personId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personId = it.getInt(PERSON_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(personId)
        val observer = Observer<History> {
            textView.text = it!!.pastFootHistory
            Glide.with(context!!).load(it!!.diabetesHistoryimage).into(historyImage)
            textView2.text = it.diabetesHistory
            textView3.text = it.drugHistory
            textView4.text = it.familyHistory
            textView5.text = it.pastMedicalHistory
            textView6.text = it.psychologyHistory
        }
        viewModel.history.observe(this, observer)

        val builder = Zoomy.Builder(activity).target(historyImage)
        builder.register()
    }

    companion object {
        @JvmStatic
        fun newInstance(personId: Int) =
            DemographicsFragment().apply {
                arguments = Bundle().apply {
                    putInt(PERSON_ID, personId)
                }
            }
    }
}