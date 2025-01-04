package com.example.sweatout.welcome.domain

import androidx.annotation.DrawableRes

data class User(
    val name: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    val gender: String,
    @DrawableRes val image: Int,
    val email: String,
    val phoneNo: String,
    val password: String,
    val activityLevel: String,
    val goal: String,
    val nationality: String,
    val nickName: String
)
