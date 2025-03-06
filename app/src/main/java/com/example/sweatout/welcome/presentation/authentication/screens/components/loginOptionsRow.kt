package com.example.sweatout.welcome.presentation.authentication.screens.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.sweatout.R

@Composable
fun LoginOptionRow(
    firstLoginAction: () -> Unit,
    secondLoginAction: () -> Unit,
    thirdLoginAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LoginOptionButton(
            painterRes = painterResource(R.drawable.google_logo),
            onClick = { firstLoginAction() }
        )
        LoginOptionButton(
            painterRes = painterResource(R.drawable.facebook),
            onClick = { secondLoginAction() }
        )
        LoginOptionButton(
            painterRes = painterResource(
                if (isSystemInDarkTheme()) R.drawable.apple
                else R.drawable
                        .apple_logo
            ),
            onClick = { thirdLoginAction() }
        )
    }
}