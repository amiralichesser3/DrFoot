package com.safari.drfoot.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.hafezie.barname.utility.InjectorActivity
import com.safari.drfoot.R
import com.safari.drfoot.adapters.CATEGORY_KEY
import com.safari.drfoot.adapters.GAMELEVEL_ID_KEY
import com.safari.drfoot.adapters.PersonAdapter
import com.safari.drfoot.entities.Person
import com.safari.drfoot.entities.PersonGameLevel
import com.safari.drfoot.viewmodels.GameLevelActivityViewModel
import kotlinx.android.synthetic.main.activity_game_level.*

class GameLevelActivity : InjectorActivity<GameLevelActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_level)
        val gameLevelId = intent!!.extras!!.getInt(GAMELEVEL_ID_KEY)
        val categoryTitle = intent!!.extras!!.getString(CATEGORY_KEY)
        titleText.text = categoryTitle
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameLevelActivityViewModel::class.java)
        viewModel.init(gameLevelId)

        val peopleObserver = Observer<List<Person>> {
            if (it == null || it.isEmpty()) return@Observer
            recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
            recyclerView.adapter = PersonAdapter(this@GameLevelActivity, it)
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
