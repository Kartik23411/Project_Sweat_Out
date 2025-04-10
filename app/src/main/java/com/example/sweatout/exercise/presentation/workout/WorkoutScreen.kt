package com.example.sweatout.exercise.presentation.workout

import android.content.Context
import android.os.PowerManager
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.exercise.domain.models.Exercise
import com.example.sweatout.exercise.presentation.WorkoutViewModal
import com.example.sweatout.exercise.presentation.music.MusicEvent
import com.example.sweatout.exercise.presentation.music.MusicViewModel
import com.example.sweatout.exercise.presentation.workout.components.LinearCountDown
import com.example.sweatout.ui.theme.SweatOutTheme
import kotlin.random.Random

@Composable
fun WorkoutScreen(
    onPauseClick: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewmodel: WorkoutViewModal = hiltViewModel(),
    musicViewModel: MusicViewModel = hiltViewModel()
) {
    // TODO add the i button functionality
    // Todo disabled the back button , add or change the toast for it

    val musicState by musicViewModel.state.collectAsState()

    val context = LocalContext.current
    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    val wakeLock = remember {
        powerManager.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
            "MyApp::MyWakelockTag"
        )
    }

    LaunchedEffect(Unit) {
        wakeLock.acquire()
    }
    DisposableEffect(Unit) {
        onDispose {
            if (wakeLock.isHeld) {
                wakeLock.release()
            }
        }
    }

    val me by viewmodel.getAllExercises.collectAsState(initial = listOf())
    val exerciseList = me.shuffled(Random.Default).take(3)
    var currentPosition by remember { mutableIntStateOf(1) }

    var isIButtonClicked by remember { mutableStateOf(false) }

    if (exerciseList.isEmpty()) {
        CircularProgressIndicator()
    }
    else {
        val exercise: Exercise = exerciseList[currentPosition - 1]

        key(currentPosition) {

            var time by remember { mutableIntStateOf(if (exercise.durationSeconds == 0) 10 else exercise.durationSeconds) }

            LaunchedEffect(exercise) {
                time = if (exercise.durationSeconds == 0) 10 else exercise.durationSeconds
            }

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
                    AsyncImage(
                        contentDescription = null,
                        model = exercise.image,
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
                                exerciseName = exercise.name,
                                onInfoClick = {isIButtonClicked = !isIButtonClicked}
                            )
                            AnimatedVisibility(
                                visible = !isIButtonClicked,
                                enter = fadeIn(
                                    animationSpec = tween(400, 100)
                                ),
                                exit = fadeOut(
                                    animationSpec = tween(200, 100)
                                )
                            ){
                                // timer display
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TimerDisplay(
                                        modifier = Modifier.weight(1f),
                                        widthFraction = .69f,
                                        cardHeight = 90.dp,
                                        totalTime = time,
                                        onend = {
                                            if (currentPosition < exerciseList.size) {
                                                viewmodel.increaseExerciseDone()
                                                viewmodel.increaseTotalTimeInSession(time)
                                                viewmodel.increaseCalBurnedInSession(exercise.caloriesBurned)
                                                currentPosition ++
                                            }
                                            else if (currentPosition == exerciseList.size) {
                                                viewmodel.increaseExerciseDone()
                                                viewmodel.increaseTotalTimeInSession(time)
                                                viewmodel.increaseCalBurnedInSession(exercise.caloriesBurned)
                                                musicViewModel.releasePlayer()
                                                onFinish()
                                            }
                                        }
                                    )

                                    //Music Playing Buttons Row
                                    MediaButtonRow(
                                        onPlayClick = { musicViewModel.onEvent(MusicEvent.PlayPause) },
                                        onPauseClick = { musicViewModel.onEvent(MusicEvent.PlayPause) },
                                        onNextClick = { musicViewModel.onEvent(MusicEvent.NextTrack) },
                                        onPreviousClick = { musicViewModel.onEvent(MusicEvent.PreviousTrack) },
                                        isPlaying = musicState.isPlaying,
                                    )

                                    // buttons area
                                    NavigationButtonsRow(
                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 8.dp, vertical = 16.dp),
                                        onSkipClick = {
                                            if (currentPosition < exerciseList.size) {
                                                currentPosition ++
                                            }
                                            else if (currentPosition == exerciseList.size) {
                                                onFinish()
                                                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT)
                                                        .show()
                                            }
                                        },
                                        onPauseClick = { onPauseClick() }
                                    )
                                }


                            }

                            AnimatedVisibility(
                                visible = isIButtonClicked,
                                enter = fadeIn(
                                    animationSpec = tween(400, 100)
                                ),
                                exit = fadeOut(
                                    animationSpec = tween(200, 100)
                                )
                            ) {
                                Column {
                                    // Muscle Involved Row
                                    Row {
                                        Text(
                                            text = "Muscles Involved",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = exercise.muscleInvolved.toString()
                                        )
                                    }


                                    Text(
                                        text = exercise.instructions
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ExerciseInfoColumn(
    modifier: Modifier = Modifier,
    exercise: Exercise
) {
    Column {
        // Muscle Involved Row
        Row {
            Text(
                text = "Muscles Involved",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = exercise.muscleInvolved.toString()
            )
        }


        Text(
            text = exercise.instructions
        )
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
        verticalAlignment = Alignment.CenterVertically
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
            modifier = Modifier
                    .padding(top = 4.dp)
                    .weight(1f)
        )
        Icon(
            painter = painterResource(R.drawable.baseline_info_outline_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
            modifier = Modifier
                    .padding(end = 8.dp)
                    .size(20.dp)
                    .clickable(onClick = { onInfoClick() })
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SweatOutTheme {
        ExerciseNameRow(
            exerciseName = "Arm Circles",
            onInfoClick = {},
            Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
        )
    }
}

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