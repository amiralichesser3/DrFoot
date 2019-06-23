package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person WHERE id in (:ids)")
    fun load(ids: List<Int>): LiveData<List<Person>>

    @Query("SELECT * FROM Person")
    fun load(): LiveData<List<Person>>

    @Query("SELECT * FROM Person WHERE id = -1")
    fun loadNone(): LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(person: Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(people: ArrayList<Person>)

    @Query("SELECT COUNT(*) FROM Person")
    fun count(): Int
}