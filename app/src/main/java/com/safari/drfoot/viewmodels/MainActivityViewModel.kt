package com.safari.drfoot.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.safari.drfoot.entities.GameLevel
import com.safari.drfoot.repositories.GameLevelRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val gameLevelRepo: GameLevelRepository) : ViewModel()  {
    lateinit var gameLevels: LiveData<List<GameLevel>>

    fun init() {
        gameLevels = gameLevelRepo.load()
    }
}