package com.safari.drfoot.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.safari.drfoot.entities.CoinPerSectionPerPerson

@Dao
interface CpsppDao {
    @Query("SELECT * FROM CoinPerSectionPerPerson WHERE personId = :personId AND sectionId = :sectionId Limit 1")
    fun load(personId: Int, sectionId: Int): LiveData<CoinPerSectionPerPerson>

    @Query("SELECT * FROM CoinPerSectionPerPerson WHERE personId = :personId AND sectionId = :sectionId Limit 1")
    fun loadSync(personId: Int, sectionId: Int): CoinPerSectionPerPerson?

    @Query("SELECT * FROM CoinPerSectionPerPerson WHERE personId IN (:personIds)")
    fun loadFromPersonIds(personIds: List<Int>): List<CoinPerSectionPerPerson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(coinPerSectionPerPerson: CoinPerSectionPerPerson)

    @Query("SELECT COUNT(*) FROM CoinPerSectionPerPerson")
    fun count(): Int
}