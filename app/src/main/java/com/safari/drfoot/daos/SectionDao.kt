package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.Section

@Dao
interface SectionDao {
    @Query("SELECT * FROM Section WHERE id in (:ids)")
    fun load(ids: List<Int>): LiveData<List<Section>>

    @Query("SELECT * FROM Section")
    fun load(): LiveData<List<Section>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(section: Section)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(sections: ArrayList<Section>)

    @Query("SELECT COUNT(*) FROM Section")
    fun count(): Int
}