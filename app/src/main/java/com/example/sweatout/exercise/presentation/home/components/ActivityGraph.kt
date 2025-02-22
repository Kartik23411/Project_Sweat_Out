package com.example.sweatout.exercise.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ActivityGraph(modifier: Modifier = Modifier) {
    Card(
        modifier,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Box(modifier.background(MaterialTheme.colorScheme.surfaceContainerHigh))
    }
}