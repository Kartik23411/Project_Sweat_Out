package com.example.sweatout.core.navigation.home.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sweatout.R

enum class DestinationClass(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val description: Int
) {
    HOME(R.string.home, Icons.Default.Home, R.string.home),
    PROFILE(R.string.profile, Icons.Default.Person, R.string.profile)
}