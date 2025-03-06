package com.example.sweatout.welcome.presentation.gender_selection

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.WelcomeNavigationButtonRow
import com.example.sweatout.welcome.presentation.gender_selection.components.CircularSelectingBox

@Composable
fun GenderSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = hiltViewModel(),
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit
) {
    var isMaleSelected by remember { mutableStateOf(false) }
    var isFemaleSelected by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Headline text
        CustomHeadlineText(textId = R.string.gender_selection_screen_headline)
        // about text
        CustomAboutText(textId = R.string.gender_selection_screen_about_text)
        // Gender Option Buttons column
        Column(
            modifier = Modifier
                    .weight(.9f)
                    .padding(vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            CircularSelectingBox(
                icon = painterResource(R.drawable.baseline_male_24),
                text = stringResource(R.string.male),
                isSelected = isMaleSelected,
                modifier = Modifier.size(dimensionResource(R.dimen.gender_select_button_size)),
                onClick = {
                    if (! isMaleSelected) {
                        isMaleSelected = true
                        isFemaleSelected = false // Deselect Female
                    }
                }
            )

            CircularSelectingBox(
                icon = painterResource(R.drawable.baseline_female_24),
                text = stringResource(R.string.female),
                isSelected = isFemaleSelected,
                modifier = Modifier.size(dimensionResource(R.dimen.gender_select_button_size)),
                onClick = {
                    if (! isFemaleSelected) {
                        isFemaleSelected = true
                        isMaleSelected = false // Deselect Male
                    }
                }
            )
        }
        // Proceed and cancel Button Row
        WelcomeNavigationButtonRow(
            onCancel = {
                onCancelClick()
            },
            onProceed = {
                if (isMaleSelected) {
                    viewModel.updateGender(context.getString(R.string.male))
                    onProceedClick()
                }
                else if (isFemaleSelected) {
                    viewModel.updateGender(context.getString(R.string.female))
                    onProceedClick()
                }
                else {
                    Toast.makeText(context, "Please select anyone option", Toast.LENGTH_SHORT).show()
                }
                Log.e("gender", "${viewModel.userUiState.value.gender} ${viewModel.userUiState.value.name}")
            },
        )
    }
}