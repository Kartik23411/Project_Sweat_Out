package com.example.sweatout.exercise.presentation.home.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun SectionSeparationText(
    @StringRes textId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(textId),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}