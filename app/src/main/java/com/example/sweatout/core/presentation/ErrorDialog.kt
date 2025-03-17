package com.example.sweatout.core.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sweatout.ui.theme.SweatOutTheme

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    errorType: String,
    errorMessage: String
) {
    AlertDialog(
        modifier = modifier.clip(RoundedCornerShape(5)),
        shape = RoundedCornerShape(15),
        onDismissRequest = { onDismiss() },
        confirmButton = {},
        dismissButton = {},
        title = {
            Text(
                text = errorType,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        text = {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                tint = MaterialTheme.colorScheme.errorContainer,
                modifier = Modifier.size(36.dp),
                contentDescription = null
            )
        },
        iconContentColor = MaterialTheme.colorScheme.onErrorContainer,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .7f),
        tonalElevation = 8.dp
    )
}