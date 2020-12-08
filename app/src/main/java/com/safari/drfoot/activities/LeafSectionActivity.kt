package com.safari.drfoot.activities

import android.animation.Animator
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.safari.drfoot.R
import com.safari.drfoot.entities.CoinPerSectionPerPerson
import com.safari.drfoot.entities.CurrentState
import com.safari.drfoot.fragments.DiagnosisFragment
import com.safari.drfoot.fragments.ManagementFragment
import com.safari.drfoot.fragments.PersonFragment
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.viewmodels.LeafSectionActivityViewModel
import kotlinx.android.synthetic.main.activity_leaf_section.*
import kotlinx.android.synthetic.main.activity_leaf_section.doctorImage
import kotlinx.android.synthetic.main.activity_leaf_section.timerText
import java.util.*

const val SECTION_KEY = "sectionKey"
class LeafSectionActivity : InjectorActivity<LeafSectionActivityViewModel>() {
    private var mp: MediaPlayer? = null
    lateinit var cpss: CoinPerSectionPerPerson
    lateinit var currentState: CurrentState
    var secondsPassed: Int = 0
    private var doctorHint = ""
    private var rootSectionId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaf_section)
        mp = MediaPlayer.create(applicationContext, R.raw.coin)
        intent.extras?.let {
            rootSectionId = it.getInt(SECTION_KEY)
            loadFragment(PersonFragment.newInstance(rootSectionId), false)
        }

        viewModel.init()

        viewModel.cpss.observe(this, Observer {
            it?.let {
                cpss = it
            }
        })

        viewModel.currentState.observe(this, Observer<CurrentState> {
            it?.let {
                currentState = it
                val coinText = " X ${it.coinCount}"
                if (coinTextView.text != coinText) {
                    YoYo.with(Techniques.BounceIn).playOn(coinImage)
                    coinTextView.text = coinText
                    makeCoinSound()
                }

                viewModel.loadPatientSync(it.selectedPersonId)?.imageLocal?.let { avatar ->
                    Glide.with(applicationContext!!).load(avatar).into(patientImage)
                }

                viewModel.seedCpss(it)
            }
        })

        hideToManagementButton()
        hideDiagnosisButton()
        hideFinishButton()

        toDiagnosisButton.setOnClickListener {
            currentState.coinCount -= cpss.coinCount
            cpss.coinCount = 0
            viewModel.saveCurrentState(currentState)
            viewModel.saveCpss(cpss)
            loadFragment(DiagnosisFragment(), true)
        }

        toManagementButton.setOnClickListener {
            loadFragment(ManagementFragment(), true)
        }

        finishButton.setOnClickListener{
            hideFinishButton()
        }

        doctorImage.setOnClickListener {
            makeDoctorFootSay(doctorHint)
        }
    }

    private fun makeCoinSound() {
        mp?.start()
    }

    fun makeDoctorFootSay(s: String) {
        if (s.isNotEmpty()) {
            YoYo.with(Techniques.Tada).playOn(doctorImage)
        }

        doctorText.animate().alpha(0f).setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                if (s.isNotEmpty()) {
                    doctorText.animate().alpha(1f).setListener(null)
                    doctorText.text = s
                }
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationStart(p0: Animator?) {

            }

        })
    }

    fun setHint(hint: String) {
        this.doctorHint = hint
    }

    fun startTimer() {
        val timer = Timer()
        val timerTask = object: TimerTask() {
            @SuppressLint("SetTextI18n")
            override fun run() {
                runOnUiThread {
                    secondsPassed++
                    var sec = (secondsPassed%60).toString()
                    var min = (secondsPassed/60).toString()
                    if (sec.length == 1) { sec = "0$sec" }
                    if (min.length == 1) { min = "0$min" }
                    timerText.text = "$min:$sec"
                }
            }
        }
        timer.scheduleAtFixedRate(timerTask, 0, 1000)
    }

    fun loadFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down)
        val t = transaction.replace(R.id.fragmentContainer, fragment)
        if (addToBackStack) {
            t.addToBackStack(fragment.tag)
        }
        transaction.commit()
    }

    fun hideToManagementButton() {
        toManagementButton.animate().translationY(200f)
    }

    fun showToManagementButton() {
        toManagementButton.animate().translationY(0f)
    }

    fun showFinishButton() {
        finishButton.animate().translationY(0f)
    }

    fun hideFinishButton() {
        finishButton.animate().translationY(200f)
    }

    fun hideDiagnosisButton() {
        toDiagnosisButton.animate().translationY(600f)
    }

    fun showDiagnosisButton() {
        toDiagnosisButton.animate().translationY(0f)
    }
}
