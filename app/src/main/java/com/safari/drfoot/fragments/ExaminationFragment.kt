package com.safari.drfoot.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.safari.drfoot.utility.InjectorFragment

import com.safari.drfoot.R
import com.safari.drfoot.entities.Examination
import com.safari.drfoot.viewmodels.ExaminationViewModel
import kotlinx.android.synthetic.main.fragment_examination.*

private const val PERSON_ID = "pid"
class ExaminationFragment : InjectorFragment<ExaminationViewModel>() {
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
        return inflater.inflate(R.layout.fragment_examination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExaminationViewModel::class.java)
        viewModel.init(personId)
        val observer = Observer<Examination> {
            textView.text = it!!.inspection
            Glide.with(context!!).load(it!!.inspectionImage).into(imageView);
            textView2.text = it!!.footwearAssessment
            textView3.text = it!!.neurologicalAssessment
            textView4.text = it!!.generalExamination
        }
        viewModel.examination.observe(this, observer)
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