package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.CurrentStateDao
import com.safari.drfoot.entities.CurrentState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentStateRepository @Inject constructor(private val currentStateDao: CurrentStateDao) {
    fun exists(): Boolean {
        return currentStateDao.count() != 0
    }

    fun load(): LiveData<CurrentState> {
        return currentStateDao.load()
    }

    fun loadSync(): CurrentState {
        return currentStateDao.loadSync()
    }

    fun save(currentState: CurrentState) {
        AsyncTask.execute {
            currentStateDao.save(currentState)
        }
    }
}