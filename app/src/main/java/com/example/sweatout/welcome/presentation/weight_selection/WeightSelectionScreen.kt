package com.example.sweatout.welcome.presentation.weight_selection

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.WelcomeNavigationButtonRow
import com.example.sweatout.welcome.presentation.components.rememberFWheelPickerState
import com.example.sweatout.welcome.presentation.weight_selection.components.WeightWheelPicker

@Composable
fun WeightSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = viewModel(),
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit
) {
    val state = rememberFWheelPickerState(35)
    val user by viewModel.userUiState.collectAsState()

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
            state = state,
            initialIndex = 35
        ) { index ->
            Text(
                index.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        // buttons row
        WelcomeNavigationButtonRow(
            onCancel = {
                onCancelClick()
            },
            onProceed = {
                viewModel.updateWeight(state.currentIndex)
                Log.e("current Weight", "${state.currentIndex} my val:  ${viewModel.userUiState.value.weight}")
                onProceedClick()
            },
        )
    }
}
