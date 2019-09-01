package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.Investigation

@Dao
interface InvestigationDao {
    @Query("SELECT * FROM Investigation")
    fun load(): LiveData<List<Investigation>>

    @Query("SELECT * FROM Investigation WHERE personId=:personId")
    fun load(personId: Int): LiveData<Investigation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(Investigation: Investigation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(Investigations: ArrayList<Investigation>)

    @Query("SELECT COUNT(*) FROM Investigation")
    fun count(): Int
}