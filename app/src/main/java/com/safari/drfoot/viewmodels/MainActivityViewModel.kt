package com.safari.drfoot.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.entities.GameLevel
import com.safari.drfoot.repositories.GameLevelRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val gameLevelRepo: GameLevelRepository) : ViewModel()  {
    lateinit var gameLevels: LiveData<List<GameLevel>>

    fun init() {
        seedDatabase()
        gameLevels = gameLevelRepo.load()
    }

    private fun seedDatabase() {
        AsyncTask.execute {
            if (!gameLevelRepo.exists()) {
                val gameLevels = arrayListOf(GameLevel(1, "Prevention", false, "https://www.uottawa.ca/health/sites/www.uottawa.ca.health/files/icons/Icon---Chiropody---V2.png"),
                    GameLevel(2, "Examination", true, null),
                    GameLevel(3, "Detection", true, null),
                    GameLevel(4, "Healing", true, null),
                    GameLevel(5, "Amputated", true, null))
                gameLevelRepo.save(gameLevels)
            }
        }
    }
}