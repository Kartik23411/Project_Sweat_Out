package com.example.sweatout.exercise.presentation.workout

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.exercise.presentation.components.BackButtonDialog
import com.example.sweatout.exercise.presentation.workout.components.CustomText

@Composable
fun WorkoutTypeSelectionScreen(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isSelected1 by rememberSaveable { mutableStateOf(false) }
    var isSelected2 by rememberSaveable { mutableStateOf(false) }
    var isSelected3 by rememberSaveable { mutableStateOf(false) }
    var isSelected4 by rememberSaveable { mutableStateOf(false) }
    var isDialogOpen by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (isDialogOpen) {
            BackButtonDialog(
                modifier = Modifier.fillMaxWidth(.95f),
                onCancel = { isDialogOpen = false },
                onConfirm = {}
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
            textId = R.string.select_workout_type,
            textStyle = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
        )

        Column(
            modifier = Modifier.fillMaxSize(.87f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WorkoutTypeElement(
                onClick = {
                    if (isSelected2 || isSelected3 || isSelected4) {
                        isSelected3 = false
                        isSelected1 = true
                        isSelected2 = false
                        isSelected4 = false
                    }
                    else {
                        isSelected1 = ! isSelected1
                    }
                },
                id = R.string.beginner,
                isSelected = isSelected1
            )
            WorkoutTypeElement(
                onClick = {
                    if (isSelected3 || isSelected1 || isSelected4) {
                        isSelected3 = false
                        isSelected1 = false
                        isSelected2 = true
                        isSelected4 = false
                    }
                    else {
                        isSelected2 = ! isSelected2
                    }
                },
                id = R.string.intermediate,
                isSelected = isSelected2
            )
            WorkoutTypeElement(
                onClick = {
                    if (isSelected2 || isSelected1 || isSelected4) {
                        isSelected3 = true
                        isSelected1 = false
                        isSelected2 = false
                        isSelected4 = false
                    }
                    else {
                        isSelected3 = ! isSelected3
                    }
                },
                id = R.string.advanced,
                isSelected = isSelected3
            )
            WorkoutTypeElement(
                onClick = {
                    if (isSelected3 || isSelected2 || isSelected1) {
                        isSelected3 = false
                        isSelected1 = false
                        isSelected2 = false
                        isSelected4 = true
                    }
                    else {
                        isSelected4 = ! isSelected4
                    }
                },
                id = R.string.create_new,
                isSelected = isSelected4
            )
        }

        // buttons area
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


@Composable
fun WorkoutTypeElement(
    onClick: () -> Unit,
    isSelected: Boolean,
    @StringRes id: Int,
    modifier: Modifier = Modifier,
) {
    MyAppButton(
        onClick = { onClick() },
        buttonText = stringResource(id),
        buttonColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surfaceContainer,
        textColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
        else
            MaterialTheme.colorScheme.onSurfaceVariant,
        roundRadius = 20,
        modifier = modifier
                .padding(vertical = dimensionResource(R.dimen.activity_level_button_padding))
                .height(dimensionResource(R.dimen.activity_level_button_height))
                .shadow(
                    if (isSelected) dimensionResource(R.dimen.activity_level_button_shadow)
                    else dimensionResource(
                        R.dimen._0dp
                    ),
                    shape = RoundedCornerShape(20),
                    ambientColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = .3f)
                )
                .fillMaxWidth(1f)
                .noRippleClickable {},
        elevation = if (isSelected) dimensionResource(R.dimen.activity_level_button_shadow)
        else dimensionResource(
            R.dimen._0dp
        )
    )
}