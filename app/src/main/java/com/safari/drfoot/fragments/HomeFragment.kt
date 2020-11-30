package com.safari.drfoot.fragments

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.safari.drfoot.R
import com.safari.drfoot.activities.LeafSectionActivity
import com.safari.drfoot.adapters.SectionAdapter
import com.safari.drfoot.utilities.contracts.MyCallback
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.utility.Navigator
import com.safari.drfoot.viewmodels.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*

const val SECTION_KEY = "sectionKey"
class HomeFragment : InjectorFragment<HomeFragmentViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        viewModel.rootSections.observe(this, Observer {
            it?.let {
                recyclerView.adapter = SectionAdapter(context!!, it, object: MyCallback<Int> {
                    override fun onSuccess(param: Int) {
                        val bundle = Bundle()
                        bundle.putInt(SECTION_KEY, param)
                        Navigator.withBundle(bundle).changeActivityFade(activity as Activity, LeafSectionActivity::class.java, false)
                    }

                    override fun onError(param: Int) {
                        // Ignored
                    }
                })
            }
        })
    }
}
