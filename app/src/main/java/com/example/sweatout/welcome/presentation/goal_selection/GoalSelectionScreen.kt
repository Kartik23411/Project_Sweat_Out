package com.example.sweatout.welcome.presentation.goal_selection

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.WelcomeNavigationButtonRow
import com.example.sweatout.welcome.presentation.goal_selection.components.GoalSelectionButton
import com.example.sweatout.welcome.presentation.models.Goal
import com.example.sweatout.welcome.presentation.models.goalsList

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GoalSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeModuleViewModel = viewModel(),
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit
) {
    val listOfGoals by rememberSaveable { mutableStateOf(goalsList) }
    var checkedGoalList by rememberSaveable { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeadlineText(textId = R.string.goal_selection_screen_headline)
        CustomAboutText(textId = R.string.goal_selection_screen_about_text)

        LazyColumn(
            modifier = Modifier.fillMaxSize(.88f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            userScrollEnabled = true
        ) {
            items(listOfGoals) { goal ->
                GoalSelectionButton(
                    modifier = Modifier
                            .fillMaxWidth(.9f)
                            .height(75.dp),
                    goal = goal
                )
            }
        }

        WelcomeNavigationButtonRow(
            onCancel = {
                Log.e("age", viewModel.userUiState.value.goals.toString())
                onCancelClick()
            },
            onProceed = {
                checkedGoalList = getSelectedGoalList(listOfGoals)
                viewModel.updateGoals(checkedGoalList)
                onProceedClick()
            },
        )
    }
}

fun getSelectedGoalList(goalList: List<Goal>): List<String> {
    val checkedGoals = mutableListOf<String>()
    for (goal in goalList) {
        if (goal.isChecked.value) {
            checkedGoals.add(goal.text)
        }
    }
    return checkedGoals
}