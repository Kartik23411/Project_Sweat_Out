package com.example.sweatout.exercise.data.mappers

import com.example.sweatout.exercise.data.networking.dto.ExerciseDto
import com.example.sweatout.exercise.domain.DifficultyLevel
import com.example.sweatout.exercise.domain.Exercise

fun ExerciseDto.toExercise(): Exercise {
    return Exercise(
        id = id,
        name = name,
        image = image,
        instructions = instructions,
        muscleInvolved = muscleInvolved.split(",").map { it.trim() },
        caloriesBurned = caloriesBurned,
        isRepetitionBased = isRepetitionBased,
        sets = sets,
        durationSeconds = durationSeconds,
        difficulty = when (difficulty.uppercase()) {
            "EASY" -> DifficultyLevel.EASY
            "MEDIUM" -> DifficultyLevel.MEDIUM
            "HARD" -> DifficultyLevel.HARD
            else -> DifficultyLevel.EASY
        },
        isEquipmentRequired = isEquipmentRequired,
        equipmentNames = if (equipmentNames == "NULL") emptyList()
        else equipmentNames.split(",")
                .map { it.trim() }
    )
}