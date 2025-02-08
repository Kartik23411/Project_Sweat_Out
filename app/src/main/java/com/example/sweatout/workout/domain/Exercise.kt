package com.example.sweatout.workout.domain

import androidx.annotation.DrawableRes

data class Exercise(
    val name: String,
    @DrawableRes val image: Int,
    val howTo: String,
    val muscleInvolved: String,
    val calBurned: Int,
    val isReputationNeeded: Boolean,
    val noOfSets: Int,
    val difficultyLevel: String,
    val isEquipmentsNeeded: Boolean,
    val equipmentsName: String
)