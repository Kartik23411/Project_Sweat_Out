package com.example.sweatout.exercise.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.R

@Composable
fun BackButtonDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onCancel() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
                Text(
                    text = "confirm"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onCancel() }
            ) {
                Text(
                    text = "cancel"
                )
            }
        },
        title = {
            Text(
                text = "Are you want to leave"
            )
        },
        text = {
            Text(
                text = "If you go back the entire session progress will be removed "
            )
        },
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f),
        tonalElevation = 8.dp
    )
}