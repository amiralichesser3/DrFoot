package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.Examination

@Dao
interface ExaminationDao {
    @Query("SELECT * FROM Examination")
    fun load(): LiveData<List<Examination>>

    @Query("SELECT * FROM Examination WHERE personId=:personId")
    fun load(personId: Int): LiveData<Examination>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(Examination: Examination)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(examinations: ArrayList<Examination>)

    @Query("SELECT COUNT(*) FROM Examination")
    fun count(): Int
}