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
import com.safari.drfoot.adapters.SectionAdapter
import com.safari.drfoot.utilities.contracts.MyCallback
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.utility.SharedPreferencesHelper
import com.safari.drfoot.viewmodels.LeafSectionFragmentViewModel
import kotlinx.android.synthetic.main.fragment_person.*

class LeafSectionFragment : InjectorFragment<LeafSectionFragmentViewModel>() {

    var parentSectionId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaf_section, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            parentSectionId = it.getInt("personId")
        }
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        viewModel.init(parentSectionId)
        viewModel.leafSections.observe(this, Observer {
            recyclerView.adapter = SectionAdapter(activity as Activity, it!!, object: MyCallback<Int> {
                override fun onSuccess(selectedLeafSectionId: Int) {
                    SharedPreferencesHelper.setString(context, "parent", "selectedLeafSectionId", selectedLeafSectionId.toString())
                    (activity as LeafSectionActivity).loadFragment(SubSectionFragment.newInstance(selectedLeafSectionId), true)
                }

                override fun onError(param: Int) {
                    // Ignored
                }

            })
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(personId: Int) =
            LeafSectionFragment().apply {
                arguments = Bundle().apply {
                    putInt("personId", personId)
                }
            }
    }

}
