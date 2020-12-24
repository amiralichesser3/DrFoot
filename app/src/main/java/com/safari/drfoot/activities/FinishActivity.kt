package com.safari.drfoot.activities

import android.arch.lifecycle.Observer
import android.media.MediaPlayer
import android.os.Bundle
import com.bumptech.glide.Glide
import com.safari.drfoot.R
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.viewmodels.FinishActivityViewModel
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : InjectorActivity<FinishActivityViewModel>() {
    private var mp: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        viewModel.init()
        viewModel.person.observe(this, Observer {
            it?.let {
                Glide.with(applicationContext).load(it.imageLocal).into(avatarImageView)
            }
        })

        val coinText = " X ${viewModel.cpss.coinCount}"
        coinTextView.text = coinText

        timerTextView.text = viewModel.cpss.timer

        mp = MediaPlayer.create(applicationContext, R.raw.win)
        mp?.start()
    }
}
