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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.sweatout.exercise.domain.models.DifficultyLevel
import com.example.sweatout.exercise.domain.models.Exercise
import com.example.sweatout.exercise.presentation.WorkoutViewModal
import com.example.sweatout.exercise.presentation.music.MusicEvent
import com.example.sweatout.exercise.presentation.music.MusicViewModel
import com.example.sweatout.exercise.presentation.workout.components.ExerciseInfoColumn
import com.example.sweatout.exercise.presentation.workout.components.ExerciseNameRow
import com.example.sweatout.exercise.presentation.workout.components.MediaButtonRow
import com.example.sweatout.exercise.presentation.workout.components.NavigationButtonsRow
import com.example.sweatout.exercise.presentation.workout.components.TimerDisplay
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

                // Control Section
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
//                        Control Section
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
                                // Timer Display, navigation buttons and music controls
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

                                    // Navigation Buttons Area
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
                                                musicViewModel.releasePlayer()
                                            }
                                        },
                                        onPauseClick = { onPauseClick() }
                                    )
                                }
                            }
//                            Exercise Information Row
                            AnimatedVisibility(
                                visible = isIButtonClicked,
                                enter = fadeIn(
                                    animationSpec = tween(400, 100)
                                ),
                                exit = fadeOut(
                                    animationSpec = tween(200, 100)
                                )
                            ) {
                                ExerciseInfoColumn(
                                    exercise = exercise
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SweatOutTheme {
        ExerciseInfoColumn(
            Modifier,
            exercise = Exercise(1, "kartik", "df", "lfjalfjalf", listOf("Cardio", "Chest", "Cardio", "Chest",
                "Shoulder"), 45, true,45,45, DifficultyLevel.EASY, true, listOf("me", "me"))
        )
    }
}