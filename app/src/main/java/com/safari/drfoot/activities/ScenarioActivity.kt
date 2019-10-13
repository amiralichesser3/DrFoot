package com.safari.drfoot.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.R
import com.safari.drfoot.adapters.PERSON_ID_KEY
import com.safari.drfoot.fragments.GameFragment1
import com.safari.drfoot.fragments.GameFragment2
import com.safari.drfoot.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.activity_scenario.*
import java.util.*

class ScenarioActivity : InjectorActivity<GameViewModel>() {

    var secondsPassed: Int = 0
    var isQuizLoaded = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenario)
        val personId = intent!!.extras!!.getInt(PERSON_ID_KEY)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        viewModel.init(personId)
        startTimer()
        loadGameFragment(personId)
    }

    private fun startTimer() {
        val timer = Timer()
        val timerTask = object: TimerTask() {
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

    public fun loadGameFragment(personId: Int) {
        isQuizLoaded = false
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, GameFragment1.newInstance(personId))
        transaction.commit()
    }

    public fun loadQuizFragment(personId: Int) {
        isQuizLoaded = true
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, GameFragment2.newInstance(personId))
        transaction.commit()
    }

    override fun onBackPressed() {
        if (isQuizLoaded) {
            loadGameFragment(intent!!.extras!!.getInt(PERSON_ID_KEY))
        } else {
            super.onBackPressed()
        }
    }
}
