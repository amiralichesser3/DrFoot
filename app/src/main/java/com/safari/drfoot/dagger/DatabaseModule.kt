package com.hafezie.barname.dagger

import android.app.Application
import android.arch.persistence.room.Room
import com.safari.drfoot.daos.GameLevelDao
import com.safari.drfoot.daos.PersonDao
import com.safari.drfoot.daos.PersonGameLevelDao
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
    fun provideDatabase(app: Application): MyDatabase {
        return Room.databaseBuilder(app, MyDatabase::class.java, "my-db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
}