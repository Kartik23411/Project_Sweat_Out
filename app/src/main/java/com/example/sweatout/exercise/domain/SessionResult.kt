package com.example.sweatout.exercise.domain

data class SessionResult(
    val totalCalBurned: Int = 0,
    val calBurnedInSession: Int = 0,
    val totalExercisesDone: Int = 0,
    val exercisesDoneInSession: Int = 0,
    val totalTimeInSession: Int = 0,
    val streak: Int = 0
)
