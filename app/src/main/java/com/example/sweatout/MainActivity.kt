package com.example.sweatout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sweatout.core.navigation.home.HomeScreenNavigationSuite
import com.example.sweatout.ui.theme.SweatOutTheme
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.activity_selection_screen.ActivitySelectionScreen
import com.example.sweatout.welcome.presentation.age_height_selection.AgeSelectionScreen
import com.example.sweatout.welcome.presentation.age_height_selection.HeightSelectionScreen
import com.example.sweatout.welcome.presentation.gender_selection.GenderSelectionScreen
import com.example.sweatout.welcome.presentation.goal_selection.GoalSelectionScreen
import com.example.sweatout.welcome.presentation.goal_selection.components.GoalSelectionButton
import com.example.sweatout.welcome.presentation.login_signup.DetailsFillUpScreen
import com.example.sweatout.welcome.presentation.login_signup.components.BasicLoginAndSignUpScreen
import com.example.sweatout.welcome.presentation.models.Goal
import com.example.sweatout.welcome.presentation.weight_selection.WeightSelectionScreen
import com.example.sweatout.workout.presentation.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val welcomeViewModel: WelcomeModuleViewModel = viewModel()
            SweatOutTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor =
                MaterialTheme
                        .colorScheme.surface
                ) { innerPadding ->
                    DetailsFillUpScreen({}, {}, viewModel = welcomeViewModel, Modifier.padding(innerPadding))
//                    BasicLoginAndSignUpScreen(
//                        modifier = Modifier.padding(innerPadding), {}, {}, R.string.signup, R
//                                .string.signin, R.string.login_headline,R.string.signup_text_intro,
//                        false
//                    )
//                    GoalSelectionScreen(modifier = Modifier.padding(innerPadding), viewModel = welcomeViewModel, {},
//                        {})
//                    ActivitySelectionScreen(modifier = Modifier.padding(innerPadding), viewModel = welcomeViewModel, {},
//                        {})
//                    AgeSelectionScreen(modifier = Modifier.padding(innerPadding), viewModel = welcomeViewModel, {},
//                        {})
//                    HeightSelectionScreen(modifier = Modifier.padding(innerPadding),viewModel = welcomeViewModel, {}, {})
//                    WeightSelectionScreen(modifier = Modifier.padding(innerPadding), viewModel =
//                    welcomeViewModel, {}, {})
//                    GenderSelectionScreen(modifier = Modifier.padding(innerPadding), viewModel =
//                    welcomeViewModel, {}, {})
                }
            }
        }
    }
}