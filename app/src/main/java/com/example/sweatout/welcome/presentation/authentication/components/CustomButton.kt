package com.example.sweatout.welcome.presentation.authentication.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sweatout.core.presentation.noRippleClickable

@Composable
fun CustomTextButton(
    @StringRes textId: Int,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.error,
    modifier: Modifier = Modifier,
) {
    TextButton(
        modifier = modifier.clickable { onClick() },
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            modifier = Modifier.noRippleClickable { },
            text = stringResource(textId),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = color, textAlign = TextAlign.Start
            )
        )
    }
}

@Composable
fun LoginOptionButton(
    painterRes: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
                .size(45.dp)
                .clip(CircleShape)
                .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterRes,
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            modifier = Modifier.clip(CircleShape)
        )
    }
}