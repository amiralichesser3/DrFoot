package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.SectionDao
import com.safari.drfoot.entities.Section
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SectionRepository @Inject constructor(private val sectionDao: SectionDao) {
    fun exists(): Boolean {
        return sectionDao.count() != 0
    }

    fun load(): LiveData<List<Section>> {
        return sectionDao.load()
    }

    fun save(section: Section) {
        AsyncTask.execute {
            sectionDao.save(section)
        }
    }

    fun save(sections: ArrayList<Section>) {
        AsyncTask.execute {
            sectionDao.save(sections)
        }
    }
}