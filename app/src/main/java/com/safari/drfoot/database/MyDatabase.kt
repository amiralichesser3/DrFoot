package com.safari.drfoot.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.safari.drfoot.daos.*
import com.safari.drfoot.entities.*

@Database(entities = [GameLevel::class,
    Person::class,
    PersonGameLevel::class,
    Demographic::class,
    History::class,
    Examination::class,
    Investigation::class,
    Me::class], version = 8)
abstract class MyDatabase : RoomDatabase() {
    abstract fun gameLevelDao(): GameLevelDao
    abstract fun personDao(): PersonDao
    abstract fun personGameLevelDao(): PersonGameLevelDao
    abstract fun demographicDao(): DemographicDao
    abstract fun historyDao(): HistoryDao
    abstract fun examinationDao(): ExaminationDao
    abstract fun investigationDao(): InvestigationDao
    abstract fun meDao(): MeDao
}