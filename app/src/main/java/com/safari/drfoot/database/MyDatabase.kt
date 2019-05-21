package com.safari.drfoot.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.safari.drfoot.daos.GameLevelDao
import com.safari.drfoot.entities.GameLevel

@Database(entities = [GameLevel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun gameLevelDao(): GameLevelDao
}