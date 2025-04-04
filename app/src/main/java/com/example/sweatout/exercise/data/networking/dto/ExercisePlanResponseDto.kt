package com.example.sweatout.exercise.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExercisePlanResponseDto(
    val data: List<ExerciseDto>
)
