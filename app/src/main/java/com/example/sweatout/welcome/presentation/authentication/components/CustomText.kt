package com.example.sweatout.welcome.presentation.authentication.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomLoginHeadline(
    modifier: Modifier = Modifier,
    @StringRes textId: Int
) {
    Text(
        modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp, top = 32.dp, start = 16.dp),
        text = stringResource(textId),
        style = MaterialTheme.typography.displayMedium.copy(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        ),
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun CustomCompanionText(
    @StringRes textId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        stringResource(textId),
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f),
        style = MaterialTheme.typography.labelLarge
    )
}