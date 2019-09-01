package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.Demographic

@Dao
interface DemographicDao {
    @Query("SELECT * FROM Demographic")
    fun load(): LiveData<List<Demographic>>

    @Query("SELECT * FROM Demographic WHERE personId=:personId")
    fun load(personId: Int): LiveData<Demographic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(demographics: Demographic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(demographics: ArrayList<Demographic>)

    @Query("SELECT COUNT(*) FROM Demographic")
    fun count(): Int
}