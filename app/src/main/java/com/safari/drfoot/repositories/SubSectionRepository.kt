package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.SubSectionDao
import com.safari.drfoot.entities.SubSection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubSectionRepository @Inject constructor(private val subSectionDao: SubSectionDao) {
    fun exists(): Boolean {
        return subSectionDao.count() != 0
    }

    fun load(): LiveData<List<SubSection>> {
        return subSectionDao.load()
    }

    fun save(subSection: SubSection) {
        AsyncTask.execute {
            subSectionDao.save(subSection)
        }
    }

    fun save(subsections: ArrayList<SubSection>) {
        AsyncTask.execute {
            subSectionDao.save(subsections)
        }
    }
}