package com.example.sweatout.core.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ThreeBounceAnimation() {

    val dots = listOf(
        remember { Animatable(0.2f) },
        remember { Animatable(0.2f) },
        remember { Animatable(0.2f) },
    )

    dots.forEachIndexed { index, animatable ->
        LaunchedEffect(animatable) {
            delay(index * 200L)
            animatable.animateTo(
                targetValue = 1f, animationSpec = infiniteRepeatable(
                    animation = tween(600, easing = FastOutLinearInEasing),
                    repeatMode = RepeatMode.Reverse,
                )
            )
        }
    }

    val dys = dots.map { it.value }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        dys.forEachIndexed { index, dy ->

            Box(
                Modifier
                        .size(10.dp)
                        .scale(dy)
                        .alpha(dy)
                        .background(color = Color.White, shape = CircleShape)
            )

        }
    }
}

@Composable
fun RotatingCircle() {

    var xRotation = remember {
        mutableStateOf(0f)
    }
    var yRotation = remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            animate(
                0f,
                180f,
                animationSpec = tween(800, easing = LinearOutSlowInEasing),
                block = { value, _ -> xRotation.value = value }
            )
            animate(
                0f,
                180f,
                animationSpec = tween(600, easing = LinearEasing),
                block = { value, _ -> yRotation.value = value }
            )
        }
    })

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                    .size(70.dp)
                    .graphicsLayer {
                        rotationX = xRotation.value
                        rotationY = yRotation.value
                    }
                    .background(Color.White, CircleShape)
        )
    }
}

@Composable
fun TypeWriterAnimation(
    baseText: String,
//    highlightedText: String,
    parts: List<String>,
    modifier: Modifier = Modifier,
) {
    var partIndex by remember { mutableStateOf(0) }
    var partText by remember { mutableStateOf("") }
    val textToDisplay = "$baseText $partText"

    LaunchedEffect(key1 = parts) {
        while (partIndex <= parts.size) {
            val part = parts[partIndex]

            part.forEachIndexed { charIndex, _ ->
                partText = part.substring(startIndex = 0, endIndex = charIndex + 1)
                delay(200)
            }
            delay(2000)

            part.forEachIndexed { charIndex, _ ->
                partText = part.substring(startIndex = 0, endIndex = part.length - (charIndex + 1))
                delay(50)
            }
            delay(1000)
            partIndex = (partIndex + 1) % parts.size
        }
    }

    Text(
        text = textToDisplay,
        style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 25.sp,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.ExtraBold
        ),
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )

}