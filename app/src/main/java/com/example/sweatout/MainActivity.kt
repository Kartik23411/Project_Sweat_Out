package com.example.sweatout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.sweatout.core.navigation.WorkOutAppNavHost
import com.example.sweatout.core.session.UserSession
import com.example.sweatout.ui.theme.SweatOutTheme
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userSession: UserSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val welcomeViewModel: WelcomeModuleViewModel = hiltViewModel()
            val navController = rememberNavController()
            val currentUser by userSession.currentUserFlow.collectAsState(initial = null)
            val context = LocalContext.current
            val startDestination =
                    if (currentUser?.goals.isNullOrEmpty()) context.getString(R.string.Nav_Welcome)
                    else context.getString(R.string.Nav_Main)
            SweatOutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.surface
                ) { innerPadding ->
                    WorkOutAppNavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding),
                        welcomeViewModel = welcomeViewModel,
                        context
                    )
                }
            }
        }
    }
}