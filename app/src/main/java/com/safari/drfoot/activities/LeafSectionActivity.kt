package com.safari.drfoot.activities

import android.animation.Animator
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.safari.drfoot.R
import com.safari.drfoot.fragments.PersonFragment
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.viewmodels.LeafSectionActivityViewModel
import kotlinx.android.synthetic.main.activity_leaf_section.*
import kotlinx.android.synthetic.main.activity_leaf_section.doctorImage
import kotlinx.android.synthetic.main.activity_leaf_section.timerText
import java.util.*
const val SECTION_KEY = "sectionKey"
class LeafSectionActivity : InjectorActivity<LeafSectionActivityViewModel>() {
    var secondsPassed: Int = 0
    private var rootSectionId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaf_section)
        intent.extras?.let {
            rootSectionId = it.getInt(SECTION_KEY)
            loadFragment(PersonFragment.newInstance(rootSectionId), false)
        }
    }

    fun makeDoctorFootSay(s: String) {
        YoYo.with(Techniques.Tada).playOn(doctorImage)
        doctorText.animate().alpha(0f).setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                if (!s.isNullOrEmpty()) {
                    doctorText.animate().alpha(1f).setListener(null)
                    doctorText.setText(s)
                }
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationStart(p0: Animator?) {

            }

        })
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

//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 2) {
//            supportFragmentManager.popBackStack()
//            return
//        }
//        super.onBackPressed()
//    }
}
