package com.example.sweatout.welcome.presentation.weight_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.ui.theme.SweatOutTheme
import com.example.sweatout.welcome.presentation.components.FWheelPickerFocusHorizontal
import com.example.sweatout.welcome.presentation.components.FWheelPickerFocusVertical
import com.example.sweatout.welcome.presentation.components.HorizontalWheelPicker
import com.example.sweatout.welcome.presentation.components.MyAppButton
import com.example.sweatout.welcome.presentation.components.VerticalWheelPicker
import com.example.sweatout.welcome.presentation.components.noRippleClickable

@Composable
fun WeightSelectionScreen(
    modifier: Modifier = Modifier,
    onCancelClick:()->Unit,
    onProceedClick:() -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // headline text
//        Text(
//            modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 32.dp),
//            text = stringResource(R.string.weight_selection_screen_headline),
//            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
//            color = MaterialTheme.colorScheme.onSurface
//        )
        // about text
        Text(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
            text = stringResource(R.string.weight_selection_screen_about_text),
            style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .7f)
        )

        HorizontalWheelPicker(
            modifier = Modifier.weight(.9f),
            count = 200,
            initialIndex = 35,
            focus = { FWheelPickerFocusHorizontal() }
        ) { index ->
            Text(
                index.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyAppButton(
                onClick = { onCancelClick() }, /*TODO() Add the clicking functionality*/
                buttonText = "Back",
                buttonColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .noRippleClickable { onCancelClick() }
            )
            Spacer(modifier = Modifier.width(24.dp))
            MyAppButton(
                onClick = {onProceedClick()}, /*TODO() Add the clicking functionality*/
                buttonText = "Proceed",
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .noRippleClickable { onProceedClick() }
            )
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
private fun WeightSelectionScreenPreview() {
    SweatOutTheme {
        WeightSelectionScreen(
            modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            {},{}
        )
    }
}