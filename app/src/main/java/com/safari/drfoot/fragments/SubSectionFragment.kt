package com.safari.drfoot.fragments

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.safari.drfoot.R
import com.safari.drfoot.activities.LeafSectionActivity
import com.safari.drfoot.adapters.SubSectionAdapter
import com.safari.drfoot.entities.SubSection
import com.safari.drfoot.utilities.contracts.MyCallback
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.viewmodels.SubSectionFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_leaf_section.*
import kotlinx.android.synthetic.main.fragment_person.recyclerView

class SubSectionFragment : InjectorFragment<SubSectionFragmentViewModel>() {

    var leafSectionId = -1

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
            leafSectionId = it.getInt("leafSectionId")
        }
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        viewModel.init(leafSectionId)
        viewModel.subsections.observe(this, Observer {
            if (it!!.isEmpty()) {
                ucImage.alpha = 1f
            } else {
                ucImage.alpha = 0f
            }
            recyclerView.adapter = SubSectionAdapter(context, it, object: MyCallback<SubSection> {
                override fun onSuccess(param: SubSection) {
                    (activity as LeafSectionActivity).loadFragment(InfoFragment.newInstance(param), true)
                }

                override fun onError(param: SubSection) {
                    // Ignored
                }

            } )
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as LeafSectionActivity).makeDoctorFootSay("")
        (activity as LeafSectionActivity).setHint("")
    }

    companion object {
        @JvmStatic
        fun newInstance(leafSectionId: Int) =
            SubSectionFragment().apply {
                arguments = Bundle().apply {
                    putInt("leafSectionId", leafSectionId)
                }
            }
    }

}
