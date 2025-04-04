package com.example.sweatout.exercise.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDto(
    val id: Int,
    val name: String,
    val image: String,
    val instructions: String,
    val muscleInvolved: String,
    val caloriesBurned: Int,
    val isRepetitionBased: Boolean,
    val sets: Int,
    val durationSeconds: Int,
    val difficulty: String,
    val isEquipmentRequired: Boolean,
    val equipmentNames: String
)

// user @SerialName("image_url") if data in response is it with that naem and we want another
// name in our dataclass