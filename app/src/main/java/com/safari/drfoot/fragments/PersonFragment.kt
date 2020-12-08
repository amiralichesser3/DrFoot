package com.safari.drfoot.fragments

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.safari.drfoot.R
import com.safari.drfoot.activities.LeafSectionActivity
import com.safari.drfoot.adapters.PersonAdapter
import com.safari.drfoot.entities.CurrentState
import com.safari.drfoot.utilities.contracts.MyCallback
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.viewmodels.PersonFragmentViewModel
import kotlinx.android.synthetic.main.fragment_person.*

class PersonFragment : InjectorFragment<PersonFragmentViewModel>() {

    var rootSectionId: Int = -1;
    lateinit var currentState: CurrentState;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            rootSectionId = it.getInt("rootSectionId")
        }
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        viewModel.init()
        viewModel.people.observe(this, Observer {
            recyclerView.adapter = PersonAdapter(activity as Activity, it!!, viewModel.cpssList, object: MyCallback<Int> {
                override fun onSuccess(personId: Int) {
                    currentState.selectedPersonId = personId
                    viewModel.saveCurrentState(currentState)
                    (activity as LeafSectionActivity).startTimer()
                    (activity as LeafSectionActivity).makeDoctorFootSay("Let's go!")
                    (activity as LeafSectionActivity).loadFragment(LeafSectionFragment.newInstance(rootSectionId), true)
                }

                override fun onError(param: Int) {
                    // Ignored
                }

            })
        })

        viewModel.currentState.observe(this, Observer {
            it?.let {
                currentState = it;
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(rootSectionId: Int) =
            PersonFragment().apply {
                arguments = Bundle().apply {
                    putInt("rootSectionId", rootSectionId)
                }
            }
    }

}
