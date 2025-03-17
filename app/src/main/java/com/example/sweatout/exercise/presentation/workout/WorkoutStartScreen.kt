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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.exercise.presentation.components.BackButtonDialog
import com.example.sweatout.exercise.presentation.workout.components.CircularCountDown
import com.example.sweatout.exercise.presentation.workout.components.CustomText

@Composable
fun WorkoutStartScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onTimeFinished: () -> Unit
) {
    var isDialogOpen by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (isDialogOpen) {
            BackButtonDialog(
                modifier = Modifier.fillMaxWidth(.95f),
                onCancel = { isDialogOpen = false },
                onConfirm = { onBackClick() }
            )
        }

        IconButton(
            onClick = { isDialogOpen = true },
            modifier = Modifier
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
            textId = R.string.get_ready,
            textStyle = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
        )

        CircularCountDown(
            Modifier.fillMaxHeight(.5f),
            totalTime = 1,
            onEnd = {
                onTimeFinished()
            }
        )
    }
}