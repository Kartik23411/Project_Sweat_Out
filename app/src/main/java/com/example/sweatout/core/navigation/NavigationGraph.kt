package com.example.sweatout.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.activity_selection_screen.ActivitySelectionScreen
import com.example.sweatout.welcome.presentation.age_height_selection.AgeSelectionScreen
import com.example.sweatout.welcome.presentation.age_height_selection.HeightSelectionScreen
import com.example.sweatout.welcome.presentation.authentication.DetailsFillUpScreen
import com.example.sweatout.welcome.presentation.authentication.LoginScreen
import com.example.sweatout.welcome.presentation.authentication.SignupScreen
import com.example.sweatout.welcome.presentation.gender_selection.GenderSelectionScreen
import com.example.sweatout.welcome.presentation.get_started.GetStartedScreen
import com.example.sweatout.welcome.presentation.goal_selection.GoalSelectionScreen
import com.example.sweatout.welcome.presentation.weight_selection.WeightSelectionScreen
import com.example.sweatout.workout.presentation.home.HomeScreen

//@Composable
fun NavGraphBuilder.welcomeGraph(
    navController: NavController,
    welcomeViewModel: WelcomeModuleViewModel,
    modifier: Modifier = Modifier
){
    navigation(startDestination = Screen.Get_Started_Screen.route, route = "welcome_graph"){

        composable(Screen.Get_Started_Screen.route) {
            GetStartedScreen(
                modifier = modifier,
                onStartedClick = {navController.navigate(Screen.Login_Screen.route)}
            )
        }

        composable(Screen.Login_Screen.route) {
            LoginScreen(
                modifier = modifier,
                viewModel = welcomeViewModel,
                onSignUpClick = {navController.navigate(Screen.Sign_Up_Screen.route)},
                onAuthenticationSucceed = {navController.navigate("main_graph"){
                    popUpTo("welcome_graph"){inclusive = true}
                } }
            )
        }

        composable(Screen.Sign_Up_Screen.route) {
            SignupScreen (
                modifier = modifier,
                viewModel = welcomeViewModel,
                onSignInClick = {navController.navigate(Screen.Login_Screen.route)},
                onAuthenticationSucceed = {navController.navigate(Screen.Fill_Details_Screen.route)}
            )
        }

        composable(Screen.Fill_Details_Screen.route) {
            DetailsFillUpScreen(
                onCancelClick = {navController.navigateUp()},
                onProceedClick = {navController.navigate(Screen.Gender_Selection_Screen.route)},
                viewModel = WelcomeModuleViewModel(),
                modifier = modifier
            )
        }

        composable(Screen.Gender_Selection_Screen.route) {
            GenderSelectionScreen(
                onCancelClick = {navController.navigateUp()},
                onProceedClick = {navController.navigate(Screen.Age_Selection_Screen.route)},
                viewModel = WelcomeModuleViewModel(),
                modifier = modifier
            )
        }

        composable(Screen.Age_Selection_Screen.route) {
            AgeSelectionScreen(
                onCancelClick = {navController.navigateUp()},
                onProceedClick = {navController.navigate(Screen.Height_Selection_Screen.route)},
                viewModel = WelcomeModuleViewModel(),
                modifier = modifier
            )
        }

        composable(Screen.Height_Selection_Screen.route) {
            HeightSelectionScreen(
                onCancelClick = {navController.navigateUp()},
                onProceedClick = {navController.navigate(Screen.Weight_Selection_Screen.route)},
                viewModel = WelcomeModuleViewModel(),
                modifier = modifier
            )
        }

        composable(Screen.Weight_Selection_Screen.route) {
            WeightSelectionScreen(
                onCancelClick = {navController.navigateUp()},
                onProceedClick = {navController.navigate(Screen.Activity_Selection_Screen.route)},
                viewModel = WelcomeModuleViewModel(),
                modifier = modifier
            )
        }

        composable(Screen.Activity_Selection_Screen.route) {
            ActivitySelectionScreen(
                onCancelClick = {navController.navigateUp()},
                onProceedClick = {navController.navigate(Screen.Goal_Selection_Screen.route)},
                viewModel = WelcomeModuleViewModel(),
                modifier = modifier
            )
        }

        composable(Screen.Goal_Selection_Screen.route) {
            GoalSelectionScreen(
                onCancelClick = {navController.navigateUp()},
                onProceedClick = {navController.navigate("main_graph"){
                    popUpTo("welcome_graph"){inclusive = true}
                } },
                viewModel = WelcomeModuleViewModel(),
                modifier = modifier
            )
        }
    }
}

fun NavGraphBuilder.HomeGraph(
    navController: NavController,
    modifier: Modifier = Modifier
){
    navigation(startDestination = Screen.WorkoutHome.route, route = "main_graph"){
        composable(Screen.WorkoutHome.route) {
            HomeScreen(
                modifier = modifier
            )
        }
    }
}