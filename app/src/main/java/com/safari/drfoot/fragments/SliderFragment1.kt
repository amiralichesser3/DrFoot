package com.safari.drfoot.fragments

import android.arch.lifecycle.Observer
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safari.drfoot.R
import com.safari.drfoot.activities.RegisterActivity
import com.safari.drfoot.entities.Me
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_slider1.*

const val UPSCALE = 1.2f
const val ORIGINALSCALE = 1f

const val ALPHADISABLED = .4f
const val ALPHAENABLED = 1f

class SliderFragment1 : InjectorFragment<RegisterViewModel>(), View.OnClickListener {

    private var selectedView: View? = null
    private var me: Me? = null

    var mp: MediaPlayer? = null
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
        return inflater.inflate(R.layout.fragment_slider1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mp = MediaPlayer.create(context, R.raw.button1)

        nursef.alpha = ALPHADISABLED
        nursem.alpha = ALPHADISABLED
        docm.alpha = ALPHADISABLED
        docf.alpha = ALPHADISABLED
        nursef.setOnClickListener(this)
        nursem.setOnClickListener(this)
        docm.setOnClickListener(this)
        docf.setOnClickListener(this)

        viewModel.init()
        viewModel.me.observe(this, Observer {
            me = it

            when (me?.imageLocal) {
                R.drawable.nurse_male -> {
                    nursem.alpha = ALPHAENABLED
                    nursem.animate().scaleX(UPSCALE).scaleY(UPSCALE).duration = 100
                    selectedView = nursem
                }
                R.drawable.nurse_female -> {
                    nursef.alpha = ALPHAENABLED
                    nursef.animate().scaleX(UPSCALE).scaleY(UPSCALE).duration = 100
                    selectedView = nursef
                }
                R.drawable.doctor_male -> {
                    docm.alpha = ALPHAENABLED
                    docm.animate().scaleX(UPSCALE).scaleY(UPSCALE).duration = 100
                    selectedView = docm
                }
                R.drawable.doctor_female -> {
                    docf.alpha = ALPHAENABLED
                    docf.animate().scaleX(UPSCALE).scaleY(UPSCALE).duration = 100
                    selectedView = docf
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SliderFragment1().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClick(view: View?) {
        mp?.start()

        if (selectedView?.id == view?.id) {
            return
        }
        selectedView?.animate()?.scaleX(ORIGINALSCALE)?.scaleY(ORIGINALSCALE)?.duration = 100
        selectedView?.alpha = ALPHADISABLED
        selectedView = view
        selectedView?.animate()?.scaleX(UPSCALE)?.scaleY(UPSCALE)?.duration = 100
        selectedView?.alpha = ALPHAENABLED;


        when {
            nursem.id == selectedView?.id -> {
                me?.imageLocal = R.drawable.nurse_male
            }
            nursef.id == selectedView?.id -> {
                me?.imageLocal = R.drawable.nurse_female
            }
            docm.id == selectedView?.id -> {
                me?.imageLocal = R.drawable.doctor_male
            }
            docf.id == selectedView?.id -> {
                me?.imageLocal = R.drawable.doctor_female
            }
        }

        viewModel.save(me)
    }
}
