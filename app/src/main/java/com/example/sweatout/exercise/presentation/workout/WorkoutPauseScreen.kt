package com.example.sweatout.exercise.presentation.workout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.exercise.presentation.components.BackButtonDialog
import com.example.sweatout.exercise.presentation.workout.components.CustomStopWatch
import com.example.sweatout.exercise.presentation.workout.components.CustomText

@Composable
fun WorkoutPauseScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onStartClick: () -> Unit
) {
    var isDialogOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (isDialogOpen) {
            BackButtonDialog(
                modifier = Modifier.fillMaxWidth(.95f),
                onConfirm = { onBackClick() },
                onCancel = { isDialogOpen = false }
            )
        }
        IconButton(
            onClick = { isDialogOpen = true },
            modifier = Modifier
                    .fillMaxHeight(.1f)
                    .padding(8.dp)
                    .align(Alignment.Start)
                    .noRippleClickable { }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }

        Spacer(Modifier.height(100.dp))

        CustomText(
            textId = R.string.pause_screen_text,
            textStyle = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
        )

        // stopwatch
        CustomStopWatch(
            modifier = Modifier.fillMaxSize(.7f)
        )


        Spacer(Modifier.height(120.dp))

        // Start button
        MyAppButton(
            modifier = Modifier
                    .fillMaxWidth(.87f)
                    .height(50.dp)
                    .noRippleClickable {},
            onClick = { onStartClick() },
            buttonText = stringResource(R.string.start),
        )
    }
}