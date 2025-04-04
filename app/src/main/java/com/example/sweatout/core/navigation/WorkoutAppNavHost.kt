package com.example.sweatout.core.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sweatout.exercise.presentation.WorkoutViewModal
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel

@Composable
fun WorkOutAppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    welcomeViewModel: WelcomeModuleViewModel = viewModel(),
    workoutViewModal: WorkoutViewModal = viewModel(),
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        welcomeGraph(navController, welcomeViewModel, Modifier, context)
        HomeGraph(navController, workoutViewModal, Modifier, context)
    }
}