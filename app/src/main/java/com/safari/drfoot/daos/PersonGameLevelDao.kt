package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.PersonGameLevel

@Dao
interface PersonGameLevelDao {
    @Query("SELECT * FROM PersonGameLevel WHERE gameLevelId=:gameLevelId")
    fun load(gameLevelId: Int): LiveData<List<PersonGameLevel>>

    @Query("SELECT * FROM PersonGameLevel")
    fun load(): LiveData<List<PersonGameLevel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(person: PersonGameLevel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(people: ArrayList<PersonGameLevel>)

    @Query("SELECT COUNT(*) FROM PersonGameLevel")
    fun count(): Int
}