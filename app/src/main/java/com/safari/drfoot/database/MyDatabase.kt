package com.safari.drfoot.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.safari.drfoot.daos.GameLevelDao
import com.safari.drfoot.daos.PersonDao
import com.safari.drfoot.daos.PersonGameLevelDao
import com.safari.drfoot.entities.GameLevel
import com.safari.drfoot.entities.Person
import com.safari.drfoot.entities.PersonGameLevel

@Database(entities = [GameLevel::class, Person::class, PersonGameLevel::class], version = 3)
abstract class MyDatabase : RoomDatabase() {
    abstract fun gameLevelDao(): GameLevelDao
    abstract fun personDao(): PersonDao
    abstract fun personGameLevelDao(): PersonGameLevelDao
}