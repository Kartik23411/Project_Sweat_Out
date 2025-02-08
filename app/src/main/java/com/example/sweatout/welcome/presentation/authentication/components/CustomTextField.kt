package com.example.sweatout.welcome.presentation.authentication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sweatout.R

@Composable
fun CustomTextField(
    value: MutableState<String?> = mutableStateOf(null),
    onValueChange: (String) -> Unit,
    placeHolder: String,
    imageVector: ImageVector,
    isPassword: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    modifier: Modifier = Modifier,
) {

    var isVisible by remember { mutableStateOf(true) }
    val customVisualTransformation: VisualTransformation =
            if (isVisible) PasswordVisualTransformation() else VisualTransformation.None

    Row(
        modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = .6f),
                    RoundedCornerShape(50)
                )
                .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // leading icon
        Icon(
            imageVector = imageVector,
            contentDescription = "Leading Icon",
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
            modifier = Modifier
                    .padding(end = 4.dp)
                    .size(20.dp)
        )

        // Text field
        BasicTextField(
            value = value.value ?: "",
            onValueChange = { onValueChange(it) },
            singleLine = true,
            visualTransformation = if (isPassword) customVisualTransformation else VisualTransformation.None,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = true,
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.value.isNullOrEmpty()) {
                        Text(
                            text = placeHolder,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .4f)
                        )
                    }
                    innerTextField() // Displays the actual input text
                }
            },
            modifier = Modifier.weight(1f)
        )
        // trailing icon
        IconButton(
            modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .padding(end = 0.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = .6f)),
            onClick = {
                if (isPassword)
                    isVisible = ! isVisible
                else
                    value.value = ""
            }
        ) {
            if (isPassword)
                Icon(
                    painter = painterResource(R.drawable.baseline_remove_red_eye_24),
                    contentDescription = "see password",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
                    modifier = Modifier.size(16.dp)
                )
            else
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "trailing icon",
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
                )
        }
    }
}