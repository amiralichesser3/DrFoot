package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.InvestigationDao
import com.safari.drfoot.entities.Investigation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvestigationRepository @Inject constructor(private val investigationDao: InvestigationDao) {

    fun exists(): Boolean {
        return investigationDao.count() != 0
    }

    fun load(): LiveData<List<Investigation>> {
        return investigationDao.load()
    }

    fun load(personId: Int): LiveData<Investigation> {
        return investigationDao.load(personId)
    }

    fun save(gameLevels: ArrayList<Investigation>) {
        AsyncTask.execute {
            investigationDao.save(gameLevels)
        }
    }
}