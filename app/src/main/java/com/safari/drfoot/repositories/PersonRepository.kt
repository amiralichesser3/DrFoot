package com.safari.drfoot.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.safari.drfoot.daos.PersonDao
import com.safari.drfoot.entities.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(private val personDao: PersonDao) {

    fun exists(): Boolean {
        return personDao.count() != 0
    }

    fun load(id: Int): LiveData<Person> {
        return personDao.load(id)
    }

    fun load(ids: List<Int>): LiveData<List<Person>> {
        return personDao.load(ids)
    }

    fun load(): LiveData<List<Person>> {
        return personDao.load()
    }

    fun loadNone(): LiveData<List<Person>> {
        return personDao.loadNone()
    }

    fun save(gameLevels: ArrayList<Person>) {
        AsyncTask.execute {
            personDao.save(gameLevels)
        }
    }
}