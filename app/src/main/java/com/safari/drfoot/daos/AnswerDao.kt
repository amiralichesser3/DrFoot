package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.Answer

@Dao
interface AnswerDao {
    @Query("SELECT * FROM Answer WHERE mode = :mode AND sectionId = :sectionId")
    fun load(mode: String, sectionId: Int): LiveData<List<Answer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(Answer: Answer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(Answers: ArrayList<Answer>)

    @Query("SELECT COUNT(*) FROM Answer")
    fun count(): Int
}