package com.safari.drfoot.fragments

import android.animation.Animator
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.safari.drfoot.R
import com.safari.drfoot.entities.Me
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_slider4.*

class SliderFragment4 : InjectorFragment<RegisterViewModel>() {

    private var me: Me? = null
    private val dialogs = arrayListOf<String>("My name is Dr.Foot, I'm going to help you throughout your investigations",
        "Just click on my avatar anytime you needed help!", "When you are ready, click play!")


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
        var dialogIndex = 0
        doctorImage.setOnClickListener {
            if (dialogIndex % 2 == 0) {
                YoYo.with(Techniques.Tada).playOn(it)
            }
            if (dialogs.size <= dialogIndex) {
                registerButton.visibility = View.VISIBLE
                YoYo.with(Techniques.FadeIn).playOn(registerButton)
                return@setOnClickListener
            }
            dialog.animate().alpha(0f).setListener(object: Animator.AnimatorListener {
                override fun onAnimationRepeat(p0: Animator?) {

                }

                override fun onAnimationEnd(p0: Animator?) {
                    dialog.text = dialogs[dialogIndex++]
                    dialog.animate().alpha(1f).setListener(null)
                }

                override fun onAnimationCancel(p0: Animator?) {

                }

                override fun onAnimationStart(p0: Animator?) {

                }

            })
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
