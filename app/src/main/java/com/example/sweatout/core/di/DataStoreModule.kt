package com.example.sweatout.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATASTORE_NAME = "user_session"

val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context) = context.dataStore
}