package com.example.sweatout.welcome.presentation.get_started

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sweatout.R
import com.example.sweatout.ui.theme.SweatOutTheme
import com.example.sweatout.welcome.presentation.components.MyAppButton
import com.example.sweatout.welcome.presentation.components.noRippleClickable

@Composable
fun GetStartedScreen(
    modifier: Modifier = Modifier,
    onStartedClick: () -> Unit
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image part
        Image(
            modifier = Modifier
                    .weight(.7f)
                    .fillMaxWidth(),
            painter = painterResource(R.drawable.snapedit_1735981720270),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        // Text and Button part
        Column(
            modifier = Modifier
                    .weight(0.4f)
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp, vertical =
                        16.dp
                    ),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(R.string.welcome_screen_text),
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 64.dp),
                style = MaterialTheme.typography.headlineLarge.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 34.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            MyAppButton(
                onClick = { onStartedClick() },
                buttonText = stringResource(R.string.get_started),
                modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(1f)
                        .noRippleClickable { },
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                buttonColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}

@PreviewLightDark
@Preview
@Composable
private fun WelcomeScreenPreview() {
    SweatOutTheme {
        GetStartedScreen(onStartedClick = {})
    }
}