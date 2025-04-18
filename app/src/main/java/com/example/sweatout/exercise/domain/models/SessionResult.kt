package com.example.sweatout.exercise.domain.models

data class SessionResult(
    val calBurnedInSession: Int = 0,
    val totalExercisesDone: Int = 0,
    val exercisesDoneInSession: Int = 0,
    val totalTimeInSession: Int = 0,
    val streak: Int = 0
)
