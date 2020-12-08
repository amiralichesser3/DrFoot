package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.CurrentState

@Dao
interface CurrentStateDao {
    @Query("SELECT * FROM CurrentState Limit 1")
    fun load(): LiveData<CurrentState>

    @Query("SELECT * FROM CurrentState Limit 1")
    fun loadSync(): CurrentState

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(person: CurrentState)

    @Query("SELECT COUNT(*) FROM CurrentState")
    fun count(): Int
}