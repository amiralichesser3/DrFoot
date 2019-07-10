package com.safari.drfoot.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hafezie.barname.utility.Toaster
import com.safari.drfoot.R
import com.safari.drfoot.adapters.CATEGORY_KEY
import com.safari.drfoot.adapters.PERSON_ID_KEY

class ScenarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenario)
        val personId = intent!!.extras!!.getInt(PERSON_ID_KEY)
        Toaster.toast(personId.toString(), applicationContext, false)
    }
}
