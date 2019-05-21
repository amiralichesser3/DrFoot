package com.safari.drfoot.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hafezie.barname.utility.InjectorActivity
import com.hafezie.barname.utility.Toaster
import com.safari.drfoot.R
import com.safari.drfoot.entities.GameLevel
import com.safari.drfoot.viewmodels.MainActivityViewModel

class MainActivity : InjectorActivity<MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        viewModel.init()
        viewModel.gameLevels.observe(this,
            Observer<List<GameLevel>> { Toaster.toast("Got em!", this@MainActivity, false) })
    }
}
