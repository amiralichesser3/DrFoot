package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.MeDao
import com.safari.drfoot.entities.Me
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeRepository @Inject constructor(private val meDao: MeDao) {
    fun exists(): Boolean {
        return meDao.count() != 0
    }

    fun load(): LiveData<Me> {
        return meDao.load()
    }

    fun loadSync(): Me {
        return meDao.loadSync()
    }

    fun save(me: Me) {
        AsyncTask.execute {
            meDao.save(me)
        }
    }
}