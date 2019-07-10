package com.safari.drfoot.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hafezie.barname.utility.Toaster
import com.safari.drfoot.R
import com.safari.drfoot.adapters.CATEGORY_KEY
import com.safari.drfoot.adapters.PERSON_ID_KEY
import com.safari.drfoot.fragments.GameFragment1
import kotlinx.android.synthetic.main.activity_scenario.*
import java.util.*

class ScenarioActivity : AppCompatActivity() {

    var secondsPassed: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenario)
        val personId = intent!!.extras!!.getInt(PERSON_ID_KEY)
        startTimer()
        loadFragment(personId)
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

    private fun loadFragment(personId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, GameFragment1.newInstance(personId))
        transaction.commit()
    }
}
