package com.example.sweatout.welcome.domain

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
    val nickName: String
)
