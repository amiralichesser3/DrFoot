package com.safari.drfoot.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hafezie.barname.utility.InjectorActivity
import com.hafezie.barname.utility.Toaster
import com.safari.drfoot.R
import com.safari.drfoot.adapters.GAMELEVEL_ID_KEY
import com.safari.drfoot.entities.GameLevel
import com.safari.drfoot.entities.Person
import com.safari.drfoot.entities.PersonGameLevel
import com.safari.drfoot.viewmodels.GameLevelActivityViewModel

class GameLevelActivity : InjectorActivity<GameLevelActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_level)
        val gameLevelId = intent!!.extras!!.getInt(GAMELEVEL_ID_KEY)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameLevelActivityViewModel::class.java)
        viewModel.init(gameLevelId)

        val peopleObserver = Observer<List<Person>> {
            if (it == null || it.isEmpty()) return@Observer
            Toaster.toast("Got ${it.size} people", applicationContext, false)
        }

        val gameLevelsObserver = Observer<List<PersonGameLevel>> {
            if (it == null || it.isEmpty()) return@Observer
            val personIds = arrayListOf<Int>()
            for (personGameLevel: PersonGameLevel in it)
            {
               personIds.add(personGameLevel.personId)
            }
            viewModel.loadPeople(personIds).observe(this@GameLevelActivity, peopleObserver)
        }

        viewModel.personGameLevels.observe(this, gameLevelsObserver)
    }
}
