package com.example.sweatout.exercise.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sweatout.exercise.domain.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM `Exercise-Table`")
    suspend fun getAllExercises(): List<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<Exercise>)

    @Query("DELETE FROM `Exercise-Table`")
    suspend fun clearExercises()

    @Query("SELECT * FROM `Exercise-Table`")
    fun observeAllExercises(): Flow<List<Exercise>>
}