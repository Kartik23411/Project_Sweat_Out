package com.example.sweatout.exercise.presentation.models

data class SessionResultUi(
    val totalCalBurned: String = "0 kcal",
    val calBurnedInSession: String = "0 cal",
    val totalExercisesDone: String = "0",
    val exercisesInSession: String = "0",
    val totalTimeInSession: String = "0 Minute",
    val streak: String = "0 days"
)

fun Int.toCal(): String {
    if (this > 999) return "$this kcal"
    else return "$this cal"
}

fun Int.toMinutes(): String {
    val hours = this / 60
    val remainingMin = this % 60
    if (hours > 0) return "$hours H $remainingMin Min"
    else return "$this Min"
}

fun Int.toDays(): String {
    return "$this days"
}