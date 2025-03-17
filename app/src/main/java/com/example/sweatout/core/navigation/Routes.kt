package com.example.sweatout.core.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("workout")
    object Profile : Screen("workout")

    // Welcome Module
    object Get_Started_Screen : Screen("welcome/get_started")
    object Login_Screen : Screen("welcome/login")
    object Sign_Up_Screen : Screen("welcome/sign_up")
    object Fill_Details_Screen : Screen("welcome/fill_details")
    object Gender_Selection_Screen : Screen("welcome/gender_selection")
    object Age_Selection_Screen : Screen("welcome/age_selection_screen")
    object Height_Selection_Screen : Screen("welcome/height_selection_screen")
    object Weight_Selection_Screen : Screen("welcome/weight_selection_screen")
    object Activity_Selection_Screen : Screen("welcome/activity_selection_screen")
    object Goal_Selection_Screen : Screen("welcome/goal_selection_screen")

    // workout
    object WorkoutHome : Screen("home/home")
    object WorkoutScreen : Screen("home/workout")
    object WorkoutStartScreen : Screen("home/workout_start")
    object WorkoutPauseScreen : Screen("home/workout_pause")
    object WorkoutCompleteScreen : Screen("home/workout_complete")
    object WorkoutTypeSelectScreen : Screen("home/workout_type_select")
}
