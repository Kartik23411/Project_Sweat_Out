package com.example.sweatout.exercise.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise-Table")
data class Exercise(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val instructions: String,
    val muscleInvolved: List<String>,
    val caloriesBurned: Int,
    val isRepetitionBased: Boolean,
    val sets: Int,
    val durationSeconds: Int,
    val difficulty: DifficultyLevel,
    val isEquipmentRequired: Boolean,
    val equipmentNames: List<String>
)

enum class DifficultyLevel {
    EASY, MEDIUM, HARD
}