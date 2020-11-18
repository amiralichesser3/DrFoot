package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.SubSection

@Dao
interface SubSectionDao {
    @Query("SELECT * FROM SubSection WHERE id in (:ids)")
    fun load(ids: List<Int>): LiveData<List<SubSection>>

    @Query("SELECT * FROM SubSection")
    fun load(): LiveData<List<SubSection>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(subSection: SubSection)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(subSections: ArrayList<SubSection>)

    @Query("SELECT COUNT(*) FROM SubSection")
    fun count(): Int
}