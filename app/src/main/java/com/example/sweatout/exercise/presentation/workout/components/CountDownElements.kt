package com.example.sweatout.exercise.presentation.workout.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sweatout.R
import com.example.sweatout.ui.theme.ds_digital_bold
import kotlinx.coroutines.delay

@Composable
fun LinearCountDown(
    modifier: Modifier = Modifier,
    totalTime: Int,
    onend: () -> Unit
) {
    // Use a key wrapper with the totalTime to force recomposition
    key(totalTime) {
        var elapsedTime by remember { mutableIntStateOf(totalTime) }

        LaunchedEffect(Unit) {  // Change to Unit to ensure it runs exactly once per composition
            elapsedTime = totalTime

            while (elapsedTime > 0) {
                delay(1000L)
                elapsedTime --
            }
            onend()
        }

        val formattedTime = formatTime(elapsedTime)

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            formattedTime.forEach { char ->
                Box(
                    modifier = Modifier
                            .width(50.dp)
                            .padding(horizontal = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = char.toString(),
                        style = MaterialTheme.typography.displayLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 90.sp,
                            fontFamily = ds_digital_bold
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

fun formatTime(millis: Int): String {
    val seconds = (millis) % 60
    val minutes = (millis / (60)) % 60

    return String.format("%02d:%02d", minutes, seconds)
}


@Composable
fun CircularCountDown(
    modifier: Modifier = Modifier,
    totalTime: Int,
    onEnd: () -> Unit
) {
    val fontSize =
            with(LocalDensity.current) { dimensionResource(R.dimen.timer_text_font_size).toSp() }

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val timerBgColor = MaterialTheme.colorScheme.surfaceContainerHighest
        val timerColor = MaterialTheme.colorScheme.primaryContainer
        val strokeWidth = dimensionResource(R.dimen.timer_stroke_width)
        val radius = dimensionResource(R.dimen.timer_circle_radius)

        var timeLeft by remember { mutableIntStateOf(totalTime) }
        val percentage = remember { mutableFloatStateOf(1f) }

        LaunchedEffect(key1 = timeLeft) {
            if (timeLeft > 0) {
                withFrameNanos { it }
                // Update time every second
                percentage.floatValue = timeLeft / totalTime.toFloat()
                delay(1000L)
                timeLeft --
            }
            else {
                onEnd()
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.size(radius * 2f)
        ) {
            Canvas(modifier = Modifier.size(radius * 2f)) {
                drawArc(
                    color = timerBgColor,
                    startAngle = - 90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )

                if (timeLeft > 0) {
                    drawArc(
                        color = timerColor,
                        startAngle = - 90f,
                        sweepAngle = 360 * percentage.floatValue,
                        useCenter = false,
                        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                }
            }

            Text(
                text = "$timeLeft",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}