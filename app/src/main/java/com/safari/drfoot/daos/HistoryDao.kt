package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History")
    fun load(): LiveData<List<History>>

    @Query("SELECT * FROM History WHERE personId=:personId")
    fun load(personId: Int): LiveData<History>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(history: History)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(histories: ArrayList<History>)

    @Query("SELECT COUNT(*) FROM History")
    fun count(): Int
}