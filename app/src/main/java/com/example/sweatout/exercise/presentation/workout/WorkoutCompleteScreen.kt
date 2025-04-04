package com.example.sweatout.exercise.presentation.workout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.exercise.presentation.workout.components.CustomText
import com.example.sweatout.exercise.presentation.workout.components.WorkoutStatCard

@Composable
fun WorkoutCompleteScreen(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit
//    viewModal: WorkoutViewModal = viewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        // Trophy Image
        Image(
            painter = painterResource(R.drawable.screenshot_2025_02_11_172713),
            contentDescription = null,
            modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(.7f)
                    .align(Alignment.CenterHorizontally)
        )

        // Greeting text
        GreetingText()

        // Dividing line
        HorizontalDivider(
            modifier = Modifier
                    .fillMaxWidth(.89f)
                    .padding(vertical = 16.dp),
            thickness = .35.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
        )

        // Session Stats Row
        SessionStatsRow(
            Modifier, "10", "453", "12"
        )

        Spacer(Modifier.height(120.dp))

        // Home button
        MyAppButton(
            modifier = Modifier
                    .fillMaxWidth(.87f)
                    .height(50.dp)
                    .noRippleClickable {},
            onClick = { onHomeClick() },
            buttonText = stringResource(R.string.home),
        )
    }
}


@Composable
fun SessionStatsRow(
    modifier: Modifier = Modifier,
    noOfWorkout: String,
    caloriesBurned: String,
    time: String
) {
    Row(
        modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WorkoutStatCard(
            modifier = Modifier
                    .height(80.dp)
                    .weight(1f),
            stateData = noOfWorkout,
            unitId = R.string.workouts
        )

        Spacer(Modifier.width(16.dp))

        WorkoutStatCard(
            modifier = Modifier
                    .height(80.dp)
                    .weight(1f),
            stateData = caloriesBurned,
            unitId = R.string.unit_cal
        )

        Spacer(Modifier.width(16.dp))

        WorkoutStatCard(
            modifier = Modifier
                    .height(80.dp)
                    .weight(1f),
            stateData = time,
            unitId = R.string.minutes
        )
    }
}

@Composable
fun GreetingText() {
    CustomText(
        textId = R.string.congrats,
        modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
        textStyle = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.primary
    )

    CustomText(
        textId = R.string.congrats_description,
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}