package com.example.sweatout.welcome.presentation.weight_selection

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.MyAppButton
import com.example.sweatout.welcome.presentation.components.noRippleClickable
import com.example.sweatout.welcome.presentation.components.rememberFWheelPickerState
import com.example.sweatout.welcome.presentation.weight_selection.components.WeightWheelPicker

@Composable
fun WeightSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = viewModel(),
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit
) {
    var selectedWeight by rememberSaveable { mutableStateOf(35) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // headline text
        CustomHeadlineText(
            textId = R.string.weight_selection_screen_headline
        )
        // about text
        CustomAboutText(
            textId = R.string.weight_selection_screen_about_text
        )
        // weight wheel picker
        WeightWheelPicker(
            modifier = Modifier.weight(.9f),
            count = 200,
            initialIndex = selectedWeight
        ) { index ->
            Text(
                index.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            selectedWeight = index - 2
        }
        // buttons row
        Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyAppButton(
                onClick = {
                    onCancelClick()
                }, /*TODO() Add the clicking functionality*/
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
                onClick = {
                    viewModel.updateWeight(selectedWeight)
                    onProceedClick()
                }, /*TODO() Add the clicking functionality*/
                buttonText = stringResource(R.string.proceed),
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .noRippleClickable {}
            )
        }
    }
}