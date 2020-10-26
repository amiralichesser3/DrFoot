package com.safari.drfoot.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.safari.drfoot.utility.InjectorActivity
import com.safari.drfoot.R
import com.safari.drfoot.adapters.GameLevelAdapter
import com.safari.drfoot.entities.GameLevel
import com.safari.drfoot.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : InjectorActivity<MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.init()

        val gameLevelObserver = Observer<List<GameLevel>> {
            if (it == null) return@Observer
            recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
            recyclerView.adapter = GameLevelAdapter(this@MainActivity, it)
        }

        viewModel.gameLevels.observe(this, gameLevelObserver)
    }
}
