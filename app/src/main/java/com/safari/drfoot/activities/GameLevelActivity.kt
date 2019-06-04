package com.safari.drfoot.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hafezie.barname.utility.Toaster
import com.safari.drfoot.R
import com.safari.drfoot.adapters.GAMELEVEL_ID_KEY

class GameLevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_level)
        val gameLevelId = intent!!.extras!!.getInt(GAMELEVEL_ID_KEY)
    }
}
