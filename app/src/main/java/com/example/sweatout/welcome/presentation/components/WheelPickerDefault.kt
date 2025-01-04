package com.example.sweatout.welcome.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.ui.theme.SweatOutTheme

@Composable
fun FWheelPickerFocusVertical(
    modifier: Modifier = Modifier,
    dividerSize: Dp = 4.dp,
    dividerColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                    .background(dividerColor)
                    .height(dividerSize)
                    .fillMaxWidth(.5f)
                    .align(Alignment.TopCenter),
        )
        Box(
            modifier = Modifier
                    .background(dividerColor)
                    .height(dividerSize)
                    .fillMaxWidth(.5f)
                    .align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun FWheelPickerFocusHorizontal() {
    Column(
        modifier = Modifier
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(100.dp))
        Icon(
            modifier = Modifier.size(90.dp),
            painter = painterResource(R.drawable.baseline_arrow_drop_up_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

@Preview
@Composable
private fun previ() {
    SweatOutTheme {
        FWheelPickerFocusHorizontal()
    }
}

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