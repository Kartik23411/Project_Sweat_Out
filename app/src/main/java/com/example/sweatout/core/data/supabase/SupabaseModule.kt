package com.example.sweatout.core.data.supabase

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseStorageManager(
        @ApplicationContext context: Context
    ): SupabaseStorageManager {
        return SupabaseStorageManager(
            context = context,
            SupabaseConstants.SUPABASE_URL,
            SupabaseConstants.SUPABASE_KEY
        )

    }
}