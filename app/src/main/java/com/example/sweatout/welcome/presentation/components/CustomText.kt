package com.example.sweatout.welcome.presentation.components

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
fun CustomHeadlineText(
    modifier: Modifier = Modifier,
    @StringRes textId: Int
) {
    Text(
        modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        text = stringResource(textId),
        style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun CustomAboutText(
    modifier: Modifier = Modifier,
    @StringRes textId: Int
) {
    Text(
        modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
        text = stringResource(textId),
        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .7f)
    )
}