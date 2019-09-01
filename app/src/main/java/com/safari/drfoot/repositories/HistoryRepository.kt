package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.HistoryDao
import com.safari.drfoot.entities.History
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(private val historyDao: HistoryDao) {

    fun exists(): Boolean {
        return historyDao.count() != 0
    }

    fun load(): LiveData<List<History>> {
        return historyDao.load()
    }

    fun load(personId: Int): LiveData<History> {
        return historyDao.load(personId)
    }

    fun save(gameLevels: ArrayList<History>) {
        AsyncTask.execute {
            historyDao.save(gameLevels)
        }
    }
}