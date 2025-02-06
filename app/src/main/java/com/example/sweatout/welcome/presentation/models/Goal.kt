package com.example.sweatout.welcome.presentation.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Goal(
    val text: String = "",
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
)

val goalsList = mutableListOf(
    Goal(
        text = "Get Fitter"
    ),
    Goal(
        text = "Gain Weight"
    ),
    Goal(
        text = "Loss Weight"
    ),
    Goal(
        text = "Building Muscles"
    ) ,
    Goal(
        text = "Improving Endurance"
    ) ,
    Goal(
        text = "Others"
    )
)