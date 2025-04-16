package com.example.sweatout.exercise.presentation.workout.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable

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