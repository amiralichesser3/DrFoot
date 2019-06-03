package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.GameLevel

@Dao
interface GameLevelDao {
    @Query("SELECT * FROM GameLevel")
    fun load(): LiveData<List<GameLevel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(gameLevel: GameLevel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(gameLevels: ArrayList<GameLevel>)

    @Query("SELECT COUNT(*) FROM GameLevel")
    fun count(): Int
}