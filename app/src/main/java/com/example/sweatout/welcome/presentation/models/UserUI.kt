package com.example.sweatout.welcome.presentation.models

import com.example.sweatout.welcome.domain.models.User

data class UserUI(
    var name: String = "",
    var age: Int = 15,
    var height: Int = 150,
    var weight: Int = 35,
    var gender: String? = null,
    var imageUrl: String? = null,
    var email: String? = null,
    var phoneNo: String? = null,
    var activityLevel: String? = null,
    var goals: List<String>? = null,
    var nationality: String = "Indian",
    var nickName: String? = null
)

fun UserUI.toUser(): User {
    return User(
        name = name,
        age = age,
        height = height,
        weight = weight,
        gender = gender.toString(),
        imageUrl = imageUrl.toString(),
        email = email.toString(),
        phoneNo = phoneNo.toString(),
        activityLevel = activityLevel.toString(),
        goals = goals ?: emptyList(),
        nationality = nationality,
        nickName = nickName.toString()
    )
}