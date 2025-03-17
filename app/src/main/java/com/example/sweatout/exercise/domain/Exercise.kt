package com.example.sweatout.exercise.domain

import androidx.annotation.DrawableRes

data class Exercise(
    val name: String,
    @DrawableRes val image: Int,
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