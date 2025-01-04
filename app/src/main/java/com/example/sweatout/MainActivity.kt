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
import com.example.sweatout.ui.theme.SweatOutTheme
import com.example.sweatout.welcome.presentation.weight_selection.WeightSelectionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SweatOutTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = MaterialTheme.colorScheme.surface
                ) { innerPadding ->
//                    HomeScreen(modifier = Modifier.fillMaxSize().padding(innerPadding))
//                    AgeSelectionScreen(modifier = Modifier.padding(innerPadding), {}, {})
//                    HeightSelectionScreen(modifier = Modifier.padding(innerPadding), {}, {})
                    WeightSelectionScreen(modifier = Modifier.padding(innerPadding), {}, {})
                }
            }
        }
    }
}