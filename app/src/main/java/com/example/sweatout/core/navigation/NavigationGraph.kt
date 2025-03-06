package com.example.sweatout.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sweatout.exercise.presentation.home.HomeScreen
import com.example.sweatout.exercise.presentation.workout.WorkoutCompleteScreen
import com.example.sweatout.exercise.presentation.workout.WorkoutPauseScreen
import com.example.sweatout.exercise.presentation.workout.WorkoutScreen
import com.example.sweatout.exercise.presentation.workout.WorkoutStartScreen
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.activity_selection_screen.ActivitySelectionScreen
import com.example.sweatout.welcome.presentation.age_height_selection.AgeSelectionScreen
import com.example.sweatout.welcome.presentation.age_height_selection.HeightSelectionScreen
import com.example.sweatout.welcome.presentation.authentication.screens.DetailsFillUpScreen
import com.example.sweatout.welcome.presentation.authentication.screens.LoginScreen
import com.example.sweatout.welcome.presentation.authentication.screens.SignupScreen
import com.example.sweatout.welcome.presentation.gender_selection.GenderSelectionScreen
import com.example.sweatout.welcome.presentation.get_started.GetStartedScreen
import com.example.sweatout.welcome.presentation.goal_selection.GoalSelectionScreen
import com.example.sweatout.welcome.presentation.weight_selection.WeightSelectionScreen

//@Composable
fun NavGraphBuilder.welcomeGraph(
    navController: NavController,
    welcomeViewModel: WelcomeModuleViewModel,
    modifier: Modifier = Modifier
) {
    navigation(startDestination = Screen.Get_Started_Screen.route, route = "welcome_graph") {

        composable(
            Screen.Get_Started_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            GetStartedScreen(
                modifier = modifier,
                onStartedClick = { navController.navigate(Screen.Login_Screen.route) }
            )
        }

        composable(
            Screen.Login_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            LoginScreen(
                modifier = modifier,
                viewModel = welcomeViewModel,
                onSignUpClick = { navController.navigate(Screen.Sign_Up_Screen.route) },
                onAuthenticationSucceed = {
                    navController.navigate("main_graph") {
                        popUpTo("welcome_graph") { inclusive = true }
                    }
                }
            )
        }

        composable(
            Screen.Sign_Up_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            SignupScreen(
                modifier = modifier,
                viewModel = welcomeViewModel,
                onSignInClick = { navController.navigate(Screen.Login_Screen.route) },
                onAuthenticationSucceed = { navController.navigate(Screen.Fill_Details_Screen.route) }
            )
        }

        composable(
            Screen.Fill_Details_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            DetailsFillUpScreen(
                onCancelClick = { navController.navigateUp() },
                onProceedClick = { navController.navigate(Screen.Gender_Selection_Screen.route) },
                viewModel = welcomeViewModel,
                modifier = modifier
            )
        }

        composable(
            Screen.Gender_Selection_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            GenderSelectionScreen(
                onCancelClick = { navController.navigateUp() },
                onProceedClick = { navController.navigate(Screen.Age_Selection_Screen.route) },
                viewModel = welcomeViewModel,
                modifier = modifier
            )
        }

        composable(
            Screen.Age_Selection_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            AgeSelectionScreen(
                onCancelClick = { navController.navigateUp() },
                onProceedClick = { navController.navigate(Screen.Height_Selection_Screen.route) },
                viewModel = welcomeViewModel,
                modifier = modifier
            )
        }

        composable(
            Screen.Height_Selection_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            HeightSelectionScreen(
                onCancelClick = { navController.navigateUp() },
                onProceedClick = { navController.navigate(Screen.Weight_Selection_Screen.route) },
                viewModel = welcomeViewModel,
                modifier = modifier
            )
        }

        composable(
            Screen.Weight_Selection_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            WeightSelectionScreen(
                onCancelClick = { navController.navigateUp() },
                onProceedClick = { navController.navigate(Screen.Activity_Selection_Screen.route) },
                viewModel = welcomeViewModel,
                modifier = modifier
            )
        }

        composable(
            Screen.Activity_Selection_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            ActivitySelectionScreen(
                onCancelClick = { navController.navigateUp() },
                onProceedClick = { navController.navigate(Screen.Goal_Selection_Screen.route) },
                viewModel = welcomeViewModel,
                modifier = modifier
            )
        }

        composable(
            Screen.Goal_Selection_Screen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            GoalSelectionScreen(
                onCancelClick = { navController.navigateUp() },
                onProceedClick = {
                    navController.navigate("main_graph") {
                        popUpTo("welcome_graph") { inclusive = true }
                    }
                },
                viewModel = welcomeViewModel,
                modifier = modifier
            )
        }
    }
}

fun NavGraphBuilder.HomeGraph(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    navigation(startDestination = Screen.WorkoutHome.route, route = "main_graph") {
        composable(
            Screen.WorkoutHome.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            HomeScreen(
                modifier = modifier,
                onStartClick = {navController.navigate(Screen.WorkoutStartScreen.route)}
            )
        }

        composable(
            Screen.WorkoutScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            WorkoutScreen(
                modifier = modifier,
                onSkipClick = {navController.navigate(Screen.WorkoutCompleteScreen.route)},
                onPauseClick = {navController.navigate(Screen.WorkoutPauseScreen.route)}
            )
        }

        composable(
            Screen.WorkoutStartScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            WorkoutStartScreen(
                modifier,
                onBackClick = { navController.navigateUp() },
                onTimeFinished = {navController.navigate(Screen.WorkoutScreen.route)}
            )
        }

        composable(
            Screen.WorkoutPauseScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            WorkoutPauseScreen(
                modifier,
                onBackClick = { navController.navigate(Screen.WorkoutHome.route) },
                onStartClick = {navController.navigate(Screen.WorkoutScreen.route)}
            )
        }

        composable(
            Screen.WorkoutCompleteScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }
        ) {
            WorkoutCompleteScreen(
                modifier,
                onHomeClick = {navController.navigate(Screen.WorkoutHome.route)}
            )
        }
    }
}