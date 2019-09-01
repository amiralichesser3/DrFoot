package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.DemographicDao
import com.safari.drfoot.entities.Demographic
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemographicRepository @Inject constructor(private val demographicDao: DemographicDao) {

    fun exists(): Boolean {
        return demographicDao.count() != 0
    }

    fun load(): LiveData<List<Demographic>> {
        return demographicDao.load()
    }

    fun load(personId: Int): LiveData<Demographic> {
        return demographicDao.load(personId)
    }

    fun save(gameLevels: ArrayList<Demographic>) {
        AsyncTask.execute {
            demographicDao.save(gameLevels)
        }
    }
}