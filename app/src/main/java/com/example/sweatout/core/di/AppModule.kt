package com.example.sweatout.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.sweatout.core.data.firebase.FirebaseAuthManager
import com.example.sweatout.core.data.supabase.SupabaseConstants
import com.example.sweatout.core.data.supabase.SupabaseStorageManager
import com.example.sweatout.core.data.supabase.networking.HttpClientFactory
import com.example.sweatout.exercise.data.ExerciseRepository
import com.example.sweatout.exercise.data.local.ExerciseDao
import com.example.sweatout.exercise.data.local.ExerciseDatabase
import com.example.sweatout.exercise.data.networking.RemoteExercisePlanDataSource
import com.example.sweatout.welcome.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.cio.CIO
import javax.inject.Singleton

private const val DATASTORE_NAME = "user_session"
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository = FirebaseAuthManager()

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClientFactory.create(CIO.create())

    @Provides
    @Singleton
    fun provideRemoteExercisePlanDataSource(httpClient: io.ktor.client.HttpClient): RemoteExercisePlanDataSource {
        return RemoteExercisePlanDataSource(httpClient)
    }

    @Provides
    @Singleton
    fun provideExerciseDatabase(@ApplicationContext context: Context): ExerciseDatabase {
        return Room.databaseBuilder(context, ExerciseDatabase::class.java, "exercise_db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    fun provideExerciseDao(database: ExerciseDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(
        remoteDataSouce: RemoteExercisePlanDataSource,
        dao: ExerciseDao,
        @ApplicationContext context: Context
    ): ExerciseRepository {
        return ExerciseRepository(remoteDataSouce, dao, context)
    }

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

    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context) = context.dataStore

}