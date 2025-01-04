package com.example.sweatout.workout.domain

import androidx.annotation.DrawableRes

data class ExerciseType(
    val exerciseList: List<Exercise>,
    val typeName: String,
    @DrawableRes val image: Int,
    val description: String
)