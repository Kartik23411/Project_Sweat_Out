package com.example.sweatout.welcome.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sweatout.ui.theme.SweatOutTheme

@Composable
fun MyAppButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    buttonColor: Color,
    buttonText: String,
    textColor: Color
) {
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(buttonColor),
        shape = RoundedCornerShape(50),
        modifier = modifier
    ) {
        Text(
            text = buttonText,
            color = textColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) { onClick() }
}

@Preview
@Composable
private fun ButtonPreview() {
    SweatOutTheme {
        MyAppButton(
            modifier = Modifier.width(200.dp),
            {},
            MaterialTheme.colorScheme.primaryContainer,
            "Proceed",
            MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}