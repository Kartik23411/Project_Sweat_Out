package com.example.sweatout.welcome.presentation.age_height_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.age_height_selection.components.Age_HeightPicker
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.MyAppButton
import com.example.sweatout.welcome.presentation.components.noRippleClickable

@Composable
fun HeightSelectionScreen(
    modifier: Modifier = Modifier,
    onCancelClick:()->Unit,
    onProceedClick:() -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeadlineText(textId = R.string.height_selection_screen_headline)
        CustomAboutText(textId = R.string.height_selection_screen_about_text)

        Age_HeightPicker(
            modifier = Modifier.weight(.9f),
            count = 280,
            initialIndex = 120,
        ) { index ->
            Text(
                index.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        // buttons row
        Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyAppButton(
                onClick = { onCancelClick() }, /*TODO() Add the clicking functionality*/
                buttonText = stringResource(R.string.back),
                buttonColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .noRippleClickable {}
            )
            Spacer(modifier = Modifier.width(24.dp))
            MyAppButton(
                onClick = {onProceedClick()}, /*TODO() Add the clicking functionality*/
                buttonText = stringResource(R.string.proceed),
                modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .noRippleClickable {}
            )
        }
    }
}