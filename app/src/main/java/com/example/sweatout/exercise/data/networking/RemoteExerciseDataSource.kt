package com.example.sweatout.exercise.data.networking

import com.example.sweatout.core.data.supabase.networking.constructUrl
import com.example.sweatout.core.data.supabase.networking.safeCall
import com.example.sweatout.core.domain.util.NetworkError
import com.example.sweatout.core.domain.util.Result
import com.example.sweatout.core.domain.util.map
import com.example.sweatout.exercise.data.mappers.toExercise
import com.example.sweatout.exercise.data.networking.dto.ExerciseDto
import com.example.sweatout.exercise.domain.DifficultyLevel
import com.example.sweatout.exercise.domain.Exercise
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteExercisePlanDataSource @Inject constructor(
    private val httpClient: HttpClient
) {

    suspend fun getExercisePlan(activityLevel: DifficultyLevel): Result<List<Exercise>, NetworkError> {
        val tableName = when (activityLevel) {
            DifficultyLevel.EASY   -> "Easy_Exercise_Plan"
            DifficultyLevel.MEDIUM -> "Medium_Exercise_Plan"
            DifficultyLevel.HARD   -> "Hard_Exercise_Plan"
        }
        return safeCall<List<ExerciseDto>> {
            httpClient.get(urlString = constructUrl("/$tableName"))
        }.map { response ->
            response.map { it.toExercise() }
        }
    }

}