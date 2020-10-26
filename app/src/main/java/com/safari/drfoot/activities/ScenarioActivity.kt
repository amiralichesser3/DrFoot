package com.safari.drfoot.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.R
import com.safari.drfoot.adapters.PERSON_ID_KEY
import com.safari.drfoot.fragments.GameFragment1
import com.safari.drfoot.fragments.GameFragment2
import com.safari.drfoot.utilities.CoinHelper
import com.safari.drfoot.utility.Navigator
import com.safari.drfoot.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.activity_scenario.*
import java.util.*

class ScenarioActivity : InjectorActivity<GameViewModel>() {

    var secondsPassed: Int = 0
    private var isQuizLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenario)
        val personId = intent!!.extras!!.getInt(PERSON_ID_KEY)
        viewModel.init(personId)
        startTimer()
        doctorTalking.animate().alpha(0f)
        coinTextView.text = CoinHelper.getCoinBalance(applicationContext).toString()
        loadGameFragment(personId)
    }

    private fun startTimer() {
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

    private fun loadGameFragment(personId: Int) {
        isQuizLoaded = false
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, GameFragment1.newInstance(personId))
        transaction.commit()
    }

    fun loadQuizFragment(personId: Int) {
        isQuizLoaded = true
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, GameFragment2.newInstance(personId))
        transaction.commit()
    }

    fun handleLoss() {
        YoYo.with(Techniques.Wobble).playOn(doctorImage)
        YoYo.with(Techniques.BounceIn).playOn(coinImage)
        coinTextView.text = CoinHelper.removeCoin(applicationContext).toString()
        talk(true)
    }

    private fun talk(isLoss: Boolean) {
        if (isLoss) {
            doctorTalking.text = getRandomLossText()
        } else {
            doctorTalking.text = getRandomSuccessText()
        }

        doctorTalking.animate().alpha(1f)
        Handler().postDelayed({ doctorTalking.animate().alpha(0f) }, 2000)
    }

    fun handleSuccess() {
        YoYo.with(Techniques.Tada).playOn(doctorImage)
        YoYo.with(Techniques.BounceIn).playOn(coinImage)
        coinTextView.text = CoinHelper.addCoin(applicationContext).toString()
        talk(false)
    }

    fun handleEnd() {
         Navigator.withouthBundle().changeActivityFade(this@ScenarioActivity, MainActivity::class.java, true)
    }

    override fun onBackPressed() {
        if (isQuizLoaded) {
            loadGameFragment(intent!!.extras!!.getInt(PERSON_ID_KEY))
        } else {
            super.onBackPressed()
        }
    }

    private fun getRandomSuccessText(): String {
        val successTexts = arrayListOf<String>()
        successTexts.add("آفرین")
        successTexts.add("احسنت")
        successTexts.add("دقیقا")
        successTexts.add("درسته")
        successTexts.add("خودشه")
        successTexts.add("بسیار عالی")
        return successTexts[(0..5).random()]
    }

    private fun getRandomLossText(): String {
        val lossTexts = arrayListOf<String>()
        lossTexts.add("دقت کن")
        lossTexts.add("متاسفم")
        lossTexts.add("اشتباه است")
        lossTexts.add("بیشتر فکر کن")
        lossTexts.add("خیر")
        return lossTexts[(0..4).random()]
    }
}
