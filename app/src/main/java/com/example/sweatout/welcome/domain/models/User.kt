package com.example.sweatout.welcome.domain.models

import com.example.sweatout.welcome.presentation.models.UserUI

data class User(
    val name: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    val gender: String,
    val imageUrl: String,
    val email: String,
    val phoneNo: String,
    val activityLevel: String,
    val goals: List<String>,
    val nationality: String,
    val nickName: String,
    val totalCaloriesBurned: Int = 0
)

fun User.toUserUI(): UserUI {
    return UserUI(
        name,
        age,
        height,
        weight,
        gender,
        imageUrl,
        email,
        phoneNo,
        activityLevel,
        goals,
        nationality,
        nickName,
        totalCaloriesBurned
    )
}
