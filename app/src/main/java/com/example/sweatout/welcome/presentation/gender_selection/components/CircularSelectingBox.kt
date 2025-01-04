package com.example.sweatout.welcome.presentation.gender_selection.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CircularSelectingBox(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    unselectedColor: Color,
    selectedColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
                .clip(CircleShape)
                .background(
                    color = if (isSelected) selectedColor else unselectedColor,
                    shape = CircleShape
                )
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = textColor
        )
        Text(
            text,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}
