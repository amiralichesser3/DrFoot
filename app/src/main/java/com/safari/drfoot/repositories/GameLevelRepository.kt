package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.GameLevelDao
import com.safari.drfoot.entities.GameLevel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameLevelRepository @Inject constructor(private val gameLevelDao: GameLevelDao) {

    fun exists(): Boolean {
        return gameLevelDao.count() != 0
    }

    fun load(): LiveData<List<GameLevel>> {
        return gameLevelDao.load()
    }

    fun save(gameLevels: ArrayList<GameLevel>) {
        AsyncTask.execute {
            gameLevelDao.save(gameLevels)
        }
    }
}