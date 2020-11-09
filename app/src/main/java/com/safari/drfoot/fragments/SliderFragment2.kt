package com.safari.drfoot.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.safari.drfoot.R
import com.safari.drfoot.entities.Me
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_slider2.*

class SliderFragment2 : InjectorFragment<RegisterViewModel>() {

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
        return inflater.inflate(R.layout.fragment_slider2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        viewModel.me.observe(this, Observer {
            me = it
            if (name.text.toString() != me?.name) {
                name.setText(me?.name)
            }
        })

        name.addTextChangedListener(object: TextWatcher   {
            override fun afterTextChanged(p0: Editable?) {
                me?.name = name.text.toString()
                viewModel.save(me)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    companion object {  
        @JvmStatic
        fun newInstance() =
            SliderFragment2().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
