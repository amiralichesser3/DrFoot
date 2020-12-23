package com.safari.drfoot.activities

import android.animation.Animator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.safari.drfoot.utility.Navigator
import com.safari.drfoot.R
import kotlinx.android.synthetic.main.activity_splash.*

const val ANIMATION_DURATION = 250L
const val DELAY = 700L

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        logoImage.alpha = 0f
        logoImage.animate().alpha(1f).setDuration(ANIMATION_DURATION).setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                Handler().postDelayed({
                    logoImage.animate().alpha(0f).setDuration(ANIMATION_DURATION).setListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(p0: Animator?) {

                        }

                        override fun onAnimationEnd(p0: Animator?) {
                            Navigator.withouthBundle().changeActivity(this@Splash, IntroActivity::class.java, true)
                        }

                        override fun onAnimationStart(p0: Animator?) {

                        }

                        override fun onAnimationCancel(p0: Animator?) {

                        }
                    }) }, DELAY)
            }

            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {

            }
        })
    }
}
