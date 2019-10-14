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
import com.safari.drfoot.entities.Demographic
import com.safari.drfoot.viewmodels.DemographicsViewModel
import kotlinx.android.synthetic.main.fragment_demographics.*
import kotlinx.android.synthetic.main.fragment_demographics.imageView

private const val PERSON_ID = "pid"
class DemographicsFragment : InjectorFragment<DemographicsViewModel>() {
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
        return inflater.inflate(R.layout.fragment_demographics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DemographicsViewModel::class.java)
        viewModel.init(personId)
        val observer = Observer<Demographic> {
            textView.text = it!!.info
            Glide.with(context!!).load(it.labResultsImage).into(imageView)
            textView2.text = it.physicalExamResults
            textView3.text = it.diabetesHistory
            textView4.text = it.history
            textView5.text = it.medication
        }
        viewModel.demographic.observe(this, observer)

        val builder = Zoomy.Builder(activity).target(imageView)
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
