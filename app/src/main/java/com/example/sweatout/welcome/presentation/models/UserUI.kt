package com.example.sweatout.welcome.presentation.models

import androidx.annotation.DrawableRes

data class UserUI(
    var name: String = "" ,
    var age: Int = 15,
    var height: Int = 150,
    var weight: Int = 35,
    var gender: String? = null,
    @DrawableRes var image: Int? = null,
    var email: String? = null,
    var phoneNo: String? = null,
    var password: String? = null,
    var activityLevel: String? = null,
    var goal: String? = null,
    var nationality: String = "Indian",
    var nickName: String? = null
)