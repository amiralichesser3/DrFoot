package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.CpsppDao
import com.safari.drfoot.entities.CoinPerSectionPerPerson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CpsppRepository @Inject constructor(private val dao: CpsppDao) {
    fun exists(): Boolean {
        return dao.count() != 0
    }

    fun load(personId: Int, sectionId: Int): LiveData<CoinPerSectionPerPerson> {
        return dao.load(personId, sectionId)
    }

    fun loadSync(personId: Int, sectionId: Int): CoinPerSectionPerPerson? {
        return dao.loadSync(personId, sectionId)
    }

    fun loadList(personIds: List<Int>): List<CoinPerSectionPerPerson> {
        return dao.loadFromPersonIds(personIds)
    }

    fun save(currentState: CoinPerSectionPerPerson) {
        AsyncTask.execute {
            dao.save(currentState)
        }
    }
}