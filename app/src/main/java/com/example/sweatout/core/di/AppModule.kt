package com.example.sweatout.core.di

import com.example.sweatout.core.data.firebase.FirebaseAuthManager
import com.example.sweatout.welcome.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository = FirebaseAuthManager()
}