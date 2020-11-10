package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.Me

@Dao
interface MeDao {
    @Query("SELECT * FROM Me Limit 1")
    fun load(): LiveData<Me>

    @Query("SELECT * FROM Me Limit 1")
    fun loadSync(): Me

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(person: Me)

    @Query("SELECT COUNT(*) FROM Me")
    fun count(): Int
}