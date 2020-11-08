package com.safari.drfoot.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safari.drfoot.R
import kotlinx.android.synthetic.main.fragment_slider1.*


const val UPSCALE = 1.2f
const val DOWNSCALE = .8f;
const val ORIGINALSCALE = 1f;

class SliderFragment1 : Fragment(), View.OnClickListener {

    private var selectedView: View? = null;
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
        mp = MediaPlayer.create(activity?.applicationContext, R.raw.buttonsound)
        nursef.setOnClickListener(this)
        nursem.setOnClickListener(this)
        docm.setOnClickListener(this)
        docf.setOnClickListener(this)

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
        if (selectedView?.id === view?.id) {
            return;
        }
        selectedView?.animate()?.scaleX(ORIGINALSCALE)?.scaleY(ORIGINALSCALE)?.setDuration(100)
        selectedView = view
        selectedView?.animate()?.scaleX(UPSCALE)?.scaleY(UPSCALE)?.setDuration(100)
        mp?.start()
    }
}
