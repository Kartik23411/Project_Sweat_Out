package com.example.sweatout.welcome.presentation.activity_selection_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.activity_selection_screen.components.CustomActivityLevelButton
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.WelcomeNavigationButtonRow

@Composable
fun ActivitySelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = viewModel(),
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit
) {

    var isSelected1 by rememberSaveable { mutableStateOf(false) }
    var isSelected2 by rememberSaveable { mutableStateOf(false) }
    var isSelected3 by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeadlineText(textId = R.string.activity_selection_screen_headline)
        CustomAboutText(textId = R.string.activity_selection_screen_about)

        // selectable buttons
        Column(
            modifier = Modifier.fillMaxSize(.87f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomActivityLevelButton(
                onClick = {
                    if (isSelected2 || isSelected3) {
                        isSelected3 = false
                        isSelected1 = true
                        isSelected2 = false
                    }
                    else {
                        isSelected1 = ! isSelected1
                    }
                },
                id = R.string.beginner,
                isSelected = isSelected1
            )
            CustomActivityLevelButton(
                onClick = {
                    if (isSelected3 || isSelected1) {
                        isSelected3 = false
                        isSelected1 = false
                        isSelected2 = true
                    }
                    else {
                        isSelected2 = ! isSelected2
                    }
                },
                id = R.string.intermediate,
                isSelected = isSelected2
            )
            CustomActivityLevelButton(
                onClick = {
                    if (isSelected2 || isSelected1) {
                        isSelected3 = true
                        isSelected1 = false
                        isSelected2 = false
                    }
                    else {
                        isSelected3 = ! isSelected3
                    }
                },
                isSelected3,
                id = R.string.advanced
            )
        }

        // Navigation buttons row
        WelcomeNavigationButtonRow(
            onCancel = {
                Log.e("activity Level", "${viewModel.userUiState.value.activityLevel}")
                onCancelClick()
            },
            onProceed = {
                viewModel.updateActivityLevel(
                    getActivityLevel(
                        context, isSelected1,
                        isSelected2, isSelected3
                    )
                )
                onProceedClick()
            },
        )
    }
}


fun getActivityLevel(context: Context, is1: Boolean, is2: Boolean, is3: Boolean): String {
    if (is1) return context.getString(R.string.beginner)
    if (is2) return context.getString(R.string.intermediate)
    if (is3) return context.getString(R.string.advanced)
    return ""
}
