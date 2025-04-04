package com.example.sweatout.welcome.presentation.goal_selection

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sweatout.R
import com.example.sweatout.core.presentation.ErrorDialog
import com.example.sweatout.core.presentation.RotatingCircle
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
    viewModel: WelcomeModuleViewModel = hiltViewModel(),
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit
) {
    val listOfGoals by rememberSaveable { mutableStateOf(goalsList) }
    var checkedGoalList by rememberSaveable { mutableStateOf<List<String>>(emptyList()) }
    val context = LocalContext.current
    val authState by viewModel.authResult.collectAsState()
    var showAnimation by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(authState.isLoading) {
        if (authState.isLoading) {
            showAnimation = true
        }
    }
    LaunchedEffect(authState.isError) {
        if (authState.isError) {
            showDialog = true
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(
            visible = showDialog,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ErrorDialog(
                Modifier.fillMaxWidth(.85f),
                onDismiss = {
                    showDialog = false
                    viewModel.toggleIsError()
                },
                errorType = "Internal Error",
                errorMessage = authState.errorMessage.toString()
            )
        }

        AnimatedVisibility(
            visible = showAnimation,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            AlertDialog(
                modifier = Modifier
                        .fillMaxWidth(.6f)
                        .height(150.dp)
                        .clip(RoundedCornerShape(5)),
                onDismissRequest = {},
                confirmButton = {},
                dismissButton = {},
                title = { RotatingCircle() }
            )
        }

        CustomHeadlineText(textId = R.string.goal_selection_screen_headline)
        CustomAboutText(textId = R.string.goal_selection_screen_about_text)

        // List of Goals
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
                            .height(dimensionResource(R.dimen.goal_select_button_height)),
                    goal = goal
                )
            }
        }

        WelcomeNavigationButtonRow(
            onCancel = {
                onCancelClick()
            },
            onProceed = {
                checkedGoalList = getSelectedGoalList(listOfGoals)
                if (checkedGoalList.isNotEmpty()) {
                    viewModel.updateGoals(checkedGoalList)
                    viewModel.updateDetails()
                    Log.e("user", "${viewModel.userUiState}")
//                    onProceedClick()
                }
                else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.goal_select_screen_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Log.e("Goals", viewModel.userUiState.value.goals.toString())
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