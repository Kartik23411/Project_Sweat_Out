package com.example.sweatout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.sweatout.core.navigation.WorkOutAppNavHost
import com.example.sweatout.exercise.presentation.workout.WorkoutCompleteScreen
import com.example.sweatout.exercise.presentation.workout.WorkoutPauseScreen
import com.example.sweatout.exercise.presentation.workout.WorkoutScreen
import com.example.sweatout.exercise.presentation.workout.WorkoutStartScreen
import com.example.sweatout.ui.theme.SweatOutTheme
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val welcomeViewModel: WelcomeModuleViewModel = viewModel()
            val navController = rememberNavController()
            SweatOutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), containerColor =
                    MaterialTheme
                            .colorScheme.surface
                ) { innerPadding ->
                    WorkOutAppNavHost(
                        navController = navController,
                        startDestination = "welcome_graph",
                        modifier = Modifier.padding(innerPadding),
                        welcomeViewModel = welcomeViewModel
                    )
//                    WorkoutCompleteScreen(Modifier.padding(innerPadding))
//                    WorkoutStartScreen(modifier = Modifier.padding(innerPadding), onBackClick = {}, onTimeFinished = {})
//                    WorkoutPauseScreen(onBackClick = {}, onStartClick = {})
//                    WorkoutScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}