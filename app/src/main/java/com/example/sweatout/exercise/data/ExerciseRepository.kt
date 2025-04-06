package com.example.sweatout.exercise.data

import android.content.Context
import android.util.Log
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.sweatout.core.domain.util.NetworkError
import com.example.sweatout.core.domain.util.Result
import com.example.sweatout.exercise.data.local.ExerciseDao
import com.example.sweatout.exercise.data.networking.RemoteExercisePlanDataSource
import com.example.sweatout.exercise.domain.models.DifficultyLevel
import com.example.sweatout.exercise.domain.models.Exercise
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(
    private val remoteDataSource: RemoteExercisePlanDataSource,
    private val exerciseDao: ExerciseDao,
    @ApplicationContext private val context: Context // Hilt annotation for context injection
) {

    suspend fun getExercisePlan(activityLevel: DifficultyLevel): Result<List<Exercise>, NetworkError> {
        val localExercises = exerciseDao.getAllExercises()

        // checking if data exists
        return if (localExercises.isNotEmpty()) {
            Log.e("Local", localExercises.toString())
            Result.Success(localExercises)
        }
        else {
            // Fetching from the supabase if not available
            when (val remoteResult = remoteDataSource.getExercisePlan(activityLevel)) {
                is Result.Success -> {
//                    saving the fetched data to room
                    exerciseDao.clearExercises()
                    exerciseDao.insertExercises(remoteResult.data)

                    // preloading the images
                    val imageLoader = ImageLoader(context)
                    remoteResult.data.forEach { exercise ->
                        val request = ImageRequest.Builder(context).data(exercise.image).build()
                        imageLoader.enqueue(request)
                    }

                    Result.Success(remoteResult.data)
                }

                is Result.Error   -> remoteResult
            }
        }
    }

    suspend fun getLocalExercise(): Result<List<Exercise>, NetworkError> {
        val localExercises = exerciseDao.getAllExercises()
        return Result.Success(localExercises)
    }

    fun getAllExercises(): Flow<List<Exercise>> = exerciseDao.observeAllExercises()
}