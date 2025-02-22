package com.example.sweatout.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MyAppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonColor: Color = MaterialTheme.colorScheme.primaryContainer,
    buttonText: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    roundRadius: Int = 50,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    style: TextStyle = MaterialTheme.typography.labelLarge
) {
    Button(
        onClick = { onClick() },
        border = border,
        elevation = ButtonDefaults.buttonElevation(elevation),
        colors = ButtonDefaults.buttonColors(buttonColor),
        shape = RoundedCornerShape(percent = roundRadius),
        modifier = modifier
    ) {
        Text(
            text = buttonText,
            color = textColor,
            style = style
        )
    }
}