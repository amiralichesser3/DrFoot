package com.safari.drfoot.dagger

import android.app.Application
import android.arch.persistence.room.Room
import com.safari.drfoot.daos.*
import com.safari.drfoot.database.MyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
internal class DatabaseModule {
    @Provides
    @Singleton
    fun provideGameLevelDao(database: MyDatabase): GameLevelDao {
        return database.gameLevelDao()
    }

    @Provides
    @Singleton
    fun providePersonDao(database: MyDatabase): PersonDao {
        return database.personDao()
    }

    @Provides
    @Singleton
    fun providePersonGameLevelDao(database: MyDatabase): PersonGameLevelDao {
        return database.personGameLevelDao()
    }

    @Provides
    @Singleton
    fun provideDemographicDao(database: MyDatabase): DemographicDao {
        return database.demographicDao()
    }

    @Provides
    @Singleton
    fun provideHistoryDao(database: MyDatabase): HistoryDao {
        return database.historyDao()
    }

    @Provides
    @Singleton
    fun provideExaminationDao(database: MyDatabase): ExaminationDao {
        return database.examinationDao()
    }

    @Provides
    @Singleton
    fun provideInvestigationDao(database: MyDatabase): InvestigationDao {
        return database.investigationDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MyDatabase {
        return Room.databaseBuilder(app, MyDatabase::class.java, "my-db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
}