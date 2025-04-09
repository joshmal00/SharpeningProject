package com.example.sharpeningapp.di

import com.example.sharpeningapp.data.HighScoreRepository
import com.example.sharpeningapp.network.HighScoreApi
import com.example.sharpeningapp.network.HighScoreApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRepository(service: HighScoreApiService) : HighScoreRepository {
        return HighScoreRepository(service)
    }

    @Provides
    @Singleton
    fun provideService() : HighScoreApiService {
        return HighScoreApi.retrofitService
    }
}