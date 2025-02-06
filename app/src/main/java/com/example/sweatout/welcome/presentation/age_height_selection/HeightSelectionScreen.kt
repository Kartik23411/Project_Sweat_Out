package com.example.sweatout.welcome.presentation.age_height_selection

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
import com.example.sweatout.welcome.presentation.age_height_selection.components.Age_HeightPicker
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.WelcomeNavigationButtonRow
import com.example.sweatout.welcome.presentation.components.rememberFWheelPickerState

@Composable
fun HeightSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = viewModel(),
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit
) {
    val state = rememberFWheelPickerState(120)
    val user by viewModel.userUiState.collectAsState()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeadlineText(textId = R.string.height_selection_screen_headline)
        CustomAboutText(textId = R.string.height_selection_screen_about_text)

        Age_HeightPicker(
            modifier = Modifier.weight(.9f),
            count = 280,
            state = state,
            initialIndex = 120,
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
                Log.e("height", "${state.currentIndex} my val:  ${user.height}")
                onCancelClick()
            },
            onProceed = {
                viewModel.updateHeight(state.currentIndex)
                onProceedClick()
            },
        )
    }
}