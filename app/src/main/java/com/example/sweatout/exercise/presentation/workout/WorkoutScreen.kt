package com.example.sweatout.exercise.presentation.workout

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.exercise.presentation.workout.components.LinearCountDown

@Composable
fun WorkoutScreen(
    modifier: Modifier = Modifier,
    onSkipClick: () -> Unit,
    onPauseClick: () -> Unit
) {
    // TODO add the skip button functionality
    // TODO add the i button functionality
    // TODO make it to change with the exercise
    // TODO use async image instead of image
    // Todo disabled the back button , add or change the toast for it

    var isLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        BackHandler {
            Toast.makeText(context, "Can't use back button", Toast.LENGTH_SHORT).show()
        }

        // Image area
        Box(
            modifier = Modifier
                    .height(425.dp)
                    .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                contentDescription = null,
                painter = painterResource(R.drawable.jumping_jacks_exercise_illustration),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
        }

        // timer and buttons area
        Box(
            modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Transparent)
        ) {
            Card(
                modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .offset(y = (- 10).dp)
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp)
                        ),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ExerciseNameRow(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                        exerciseName = "Jumping Jacks",
                        onInfoClick = {}
                    )

                    // timer display
                    TimerDisplay(
                        modifier = Modifier.weight(1f),
                        widthFraction = .69f,
                        cardHeight = 90.dp,
                        totalTime = 100
                    )

                    MediaButtonRow(
                        onPlayClick = {
                            isLoading = ! isLoading
                            Log.e("Button", "Play")
                        },
                        onPauseClick = {
                            isLoading = ! isLoading
                            Log.e("Button", "Pause")
                        },
                        onNextClick = { Log.e("Button", "next") },
                        onPreviousClick = { Log.e("Button", "previous") },
                        isPlaying = isLoading
                    )

                    // buttons area
                    NavigationButtonsRow(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 16.dp),
                        onSkipClick = { onSkipClick() },
                        onPauseClick = { onPauseClick() }
                    )
                }
            }
        }
    }
}

@Composable
fun MediaButtonRow(
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        IconButton(
            onClick = { onPreviousClick() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
            )
        }
        IconButton(
            onClick = {
                if (isPlaying) onPauseClick() else onPlayClick()
            }
        ) {
            Icon(
                painter = painterResource(if (isPlaying) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
            )
        }
        IconButton(
            onClick = { onNextClick() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ExerciseNameRow(
    exerciseName: String,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = exerciseName,
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = Modifier
                    .size(16.dp)
                    .padding(top = 2.dp)
                    .clickable(onClick = { onInfoClick() })
        )
    }
}

@Composable
fun TimerDisplay(
    modifier: Modifier = Modifier,
    totalTime: Int,
    cardHeight: Dp,
    widthFraction: Float
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
            LinearCountDown(totalTime = totalTime)
        }
    }
}

@Composable
fun NavigationButtonsRow(
    onSkipClick: () -> Unit,
    onPauseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        MyAppButton(
            onClick = { onSkipClick() },
            buttonText = stringResource(R.string.skip),
            buttonColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            textColor = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .noRippleClickable { }
        )
        MyAppButton(
            onClick = { onPauseClick() },
            modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .noRippleClickable {},
            buttonText = stringResource(R.string.pause)
        )
    }
}