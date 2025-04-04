package com.example.sweatout.exercise.presentation.models

import java.math.BigDecimal
import java.math.RoundingMode

data class SessionResultUi(
    val totalCalBurned: String = "0 kcal",
    val calBurnedInSession: String = "0 cal",
    val totalExercisesDone: String = "0",
    val exercisesInSession: String = "0",
    val totalTimeInSession: String = "0 Minute",
    val streak: String = "0 days"
)

fun Int.toCal(): String {
    if (this > 999) return "$this k"
    else return "$this"
}

fun Int.toMinutes(): String {
    val minutes = this / 60
    return BigDecimal(minutes).setScale(2, RoundingMode.HALF_UP).toDouble().toString()
}

fun Int.toDays(): String {
    return "$this days"
}