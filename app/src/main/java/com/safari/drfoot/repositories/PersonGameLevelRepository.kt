package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.PersonGameLevelDao
import com.safari.drfoot.entities.PersonGameLevel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PersonGameLevelRepository @Inject constructor(private val personGameLevelDao: PersonGameLevelDao) {

    fun exists(): Boolean {
        return personGameLevelDao.count() != 0
    }

    fun load(gameLevelId: Int): LiveData<List<PersonGameLevel>> {
        return personGameLevelDao.load(gameLevelId)
    }

    fun load(): LiveData<List<PersonGameLevel>> {
        return personGameLevelDao.load()
    }

    fun save(gameLevels: ArrayList<PersonGameLevel>) {
        AsyncTask.execute {
            personGameLevelDao.save(gameLevels)
        }
    }
}