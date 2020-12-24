package com.safari.drfoot.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.safari.drfoot.R
import com.safari.drfoot.utility.Navigator
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        foot1.setOnClickListener {
            Navigator.withouthBundle().changeActivityFade(this, RegisterActivity::class.java, false)
        }

        foot2.setOnClickListener(this)
        foot3.setOnClickListener(this)
        foot4.setOnClickListener(this)
    }

    override fun onClick(it: View?) {
        YoYo.with(Techniques.Wobble).playOn(it)
    }
}
