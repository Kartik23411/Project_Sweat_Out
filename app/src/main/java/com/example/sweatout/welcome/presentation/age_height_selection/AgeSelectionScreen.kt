package com.example.sweatout.welcome.presentation.age_height_selection

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.age_height_selection.components.Age_HeightPicker
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.WelcomeNavigationButtonRow
import com.example.sweatout.welcome.presentation.components.rememberFWheelPickerState

@Composable
fun AgeSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = hiltViewModel(),
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit
) {
    val state = rememberFWheelPickerState(15)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeadlineText(textId = R.string.age_selection_screen_headline)
        CustomAboutText(textId = R.string.age_selection_screen_about_text)

        Age_HeightPicker(
            modifier = Modifier.weight(.9f),
            count = 100,
            state = state,
            initialIndex = 15,
        ) { index ->
            Text(
                index.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        WelcomeNavigationButtonRow(
            onCancel = {
                onCancelClick()
            },
            onProceed = {
                viewModel.updateAge(state.currentIndex)
                Log.e(
                    "age",
                    "${state.currentIndex} my val:  ${viewModel.userUiState.value.age} ${viewModel.userUiState.value.gender}"
                )
                onProceedClick()
            },
        )
    }
}