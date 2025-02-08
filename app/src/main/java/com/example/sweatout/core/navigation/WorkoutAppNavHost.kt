package com.example.sweatout.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel

@Composable
fun WorkOutAppNavHost(
    navController: NavHostController,
    startDestination:String,
    modifier: Modifier = Modifier,
    welcomeViewModel: WelcomeModuleViewModel = viewModel()
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
//            if(startDestination == "welcome_graph")
            welcomeGraph(navController, welcomeViewModel, Modifier)
//            else
            HomeGraph(navController, Modifier)  // This includes `mod1` and `mod2`
        }
}