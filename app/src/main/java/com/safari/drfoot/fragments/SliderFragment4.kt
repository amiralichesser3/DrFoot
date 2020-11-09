package com.safari.drfoot.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safari.drfoot.R
import com.safari.drfoot.entities.Me
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_slider4.*

class SliderFragment4 : InjectorFragment<RegisterViewModel>() {

    private var me: Me? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        viewModel.me.observe(this, Observer { me = it })
        registerButton.setOnClickListener {
            me?.isComplete = true
            viewModel.save(me)
        }
    }

    companion object {  
        @JvmStatic
        fun newInstance() =
            SliderFragment4().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
