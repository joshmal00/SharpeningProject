package com.example.sharpeningapp.di

import android.content.Context
import androidx.room.Room
import com.example.sharpeningapp.data.HighScoreRepository
import com.example.sharpeningapp.data.LeadersDao
import com.example.sharpeningapp.data.LeadersDatabase
import com.example.sharpeningapp.network.HighScoreApi
import com.example.sharpeningapp.network.HighScoreApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(dao: LeadersDao, service: HighScoreApiService) : HighScoreRepository {
        return HighScoreRepository(dao, service)
    }

    @Provides
    @Singleton
    fun provideService() : HighScoreApiService {
        return HighScoreApi.retrofitService
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : LeadersDatabase {
        return Room
            .databaseBuilder(
                context,
                LeadersDatabase::class.java,
                "leaders_database"
            )
            .build()
    }

    @Provides
    fun provideDao(database: LeadersDatabase) : LeadersDao {
        return database.leadersDao()
    }
}