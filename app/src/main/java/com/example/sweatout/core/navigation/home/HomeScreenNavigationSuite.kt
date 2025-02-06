package com.example.sweatout.core.navigation.home

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.core.navigation.home.components.DestinationClass
import com.example.sweatout.workout.presentation.home.HomeScreen

@Composable
fun HomeScreenNavigationSuite(
    modifier: Modifier = Modifier
) {
    var currentDestination by rememberSaveable { mutableStateOf(DestinationClass.HOME) }

    NavigationSuiteScaffold(
        modifier = Modifier,
        navigationSuiteItems = {
            DestinationClass.entries.forEach {
                item(
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = stringResource(it.description)
                        )
                    },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it },
                    label = { Text(text = stringResource(it.label)) },
                alwaysShowLabel = true,
//                badge = TODO(),
//                colors = TODO(),
                )
            }

        },
    ){
        HomeScreen(modifier)
    }

//    NavigationBar {
//
//    }
}