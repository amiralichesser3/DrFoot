package com.safari.drfoot.fragments

import android.animation.Animator
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

import com.safari.drfoot.R
import com.safari.drfoot.activities.LeafSectionActivity
import com.safari.drfoot.adapters.AnswerAdapter
import com.safari.drfoot.entities.Answer
import com.safari.drfoot.entities.CoinPerSectionPerPerson
import com.safari.drfoot.entities.CurrentState
import com.safari.drfoot.utilities.contracts.MyCallback
import com.safari.drfoot.utility.InjectorFragment
import com.safari.drfoot.viewmodels.DiagnosisFragmentViewModel
import kotlinx.android.synthetic.main.fragment_diagnosis.*

class DiagnosisFragment : InjectorFragment<DiagnosisFragmentViewModel>() {

    lateinit var currentState: CurrentState
    var coinCount = 4
    var cpss: CoinPerSectionPerPerson? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diagnosis, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(context!!).load("https://cdn2.iconfinder.com/data/icons/medical-butterscotch-vol-1/512/Diagnostics-512.png").into(diagnosisIconImage)
        Handler().postDelayed({
            if (splash != null) {
                YoYo.with(Techniques.FadeOut).duration(500).withListener(object: Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator?) {

                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        recyclerView?.animate()?.alpha(1f)
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationStart(p0: Animator?) {

                    }

                }).playOn(splash)
            }

        }, 1200)
        recyclerView.alpha = 0f
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.init()
        viewModel.cpss.observe(this, Observer {
            it?.let {
                cpss = it
            }
        })
        viewModel.currentState.observe(this, Observer {
            it?.let {
                currentState = it
            }
        })
        viewModel.diagnosisAnswers.observe(this, Observer {
            it?.let {
                recyclerView.adapter = AnswerAdapter(it, object: MyCallback<Answer> {
                    override fun onSuccess(param: Answer) {
                        currentState.coinCount += coinCount
                        viewModel.saveCurrentState(currentState)
                        cpss?.let { c ->
                            c.coinCount += coinCount
                            viewModel.saveCpss(c)
                        }

                        (activity as LeafSectionActivity).makeDoctorFootSay("Correct!")
                    }

                    override fun onError(param: Answer) {
                        (activity as LeafSectionActivity).makeDoctorFootSay("No!")
                        if (coinCount != 0) {
                            coinCount--
                        }
                    }

                }, object: MyCallback<Boolean> {
                    override fun onSuccess(param: Boolean) {
                        if (param) {
                            (activity as LeafSectionActivity).showToManagementButton()
                        }
                    }

                    override fun onError(param: Boolean) {
                        // Ignored
                    }
                })
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as LeafSectionActivity).makeDoctorFootSay("What is your diagnosis?")
    }

    override fun onPause() {
        super.onPause()
        (activity as LeafSectionActivity).makeDoctorFootSay("")
        (activity as LeafSectionActivity).hideToManagementButton()
    }

}