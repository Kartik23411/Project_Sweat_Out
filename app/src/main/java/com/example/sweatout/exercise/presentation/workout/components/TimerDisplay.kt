package com.example.sweatout.exercise.presentation.workout.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun TimerDisplay(
    modifier: Modifier = Modifier,
    totalTime: Int,
    cardHeight: Dp,
    widthFraction: Float,
    onend: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(widthFraction)
                .height(cardHeight)
        ) {
            LinearCountDown(totalTime = totalTime, onend = { onend() })
        }
    }
}