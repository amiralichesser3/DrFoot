package com.safari.drfoot.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.safari.drfoot.R
import com.safari.drfoot.activities.ScenarioActivity
import kotlinx.android.synthetic.main.fragment_next_stage.*


private const val PERSON_ID = "pid"
class NextStageFragment : Fragment() {
    private var personId: Int = 0
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
        return inflater.inflate(R.layout.fragment_next_stage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener(View.OnClickListener {
            (this.activity as ScenarioActivity).loadQuizFragment(personId)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NextStageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
