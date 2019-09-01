package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.ExaminationDao
import com.safari.drfoot.entities.Examination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExaminationRepository @Inject constructor(private val examinationDao: ExaminationDao) {

    fun exists(): Boolean {
        return examinationDao.count() != 0
    }

    fun load(): LiveData<List<Examination>> {
        return examinationDao.load()
    }

    fun load(personId: Int): LiveData<Examination> {
        return examinationDao.load(personId)
    }

    fun save(gameLevels: ArrayList<Examination>) {
        AsyncTask.execute {
            examinationDao.save(gameLevels)
        }
    }
}