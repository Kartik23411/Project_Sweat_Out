package com.example.sweatout.welcome.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FWheelPickerDisplayScope.DefaultWheelPickerDisplay(
    index: Int,
) {
    val focused = index == state.currentIndexSnapshot
    val animateScale by animateFloatAsState(
        targetValue = if (focused) 1.0f else 0.8f,
        label = "Wheel picker item scale",
    )
    Box(
        modifier = Modifier.graphicsLayer {
            this.alpha = if (focused) 1.0f else 0.4f
            this.scaleX = animateScale
            this.scaleY = animateScale
        }
    ) {
        Content(index)
    }
}