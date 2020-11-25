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
import com.safari.drfoot.adapters.SectionAdapter
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.viewmodels.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : InjectorFragment<HomeFragmentViewModel>() {

    lateinit var adapter: SectionAdapter

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
                adapter = SectionAdapter(activity as Activity, it)
                recyclerView.adapter = adapter
            }
        })
    }
}
