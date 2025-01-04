package com.example.sweatout.workout.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sweatout.R
import com.example.sweatout.ui.theme.SweatOutTheme
import com.example.sweatout.workout.presentation.home.components.ActivityGraph
import com.example.sweatout.workout.presentation.home.components.HealthMetricCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
    ) {
        // Top bar row
        Row (
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            // Profile and Greeting Row
            Row(
                modifier = Modifier.weight(1f)
            ){
                // Profile Image
                IconButton(
                    modifier = Modifier.size(50.dp),
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.onSurface)
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(R.drawable.image_restored),
//                fallback = painterResource(R.drawable.baseline_account_circle_24),
//                placeholder = painterResource(R.drawable.baseline_account_circle_24),
//                error = painterResource(R.drawable.baseline_account_circle_24),
                        contentDescription = "User Image",
                        modifier = Modifier
                                .clip(CircleShape)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    shape = CircleShape
                                ),
                        alignment = Alignment.Center,
                    )
                }
                // Greeting Text Column
                Column(
                    modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                ){
                    Text(
                        text = "Welcome Back 🎉",
                        style = (MaterialTheme.typography.titleSmall).copy(fontSize = 14.sp,
                            lineHeight = 12.sp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .75f)
                    )
                    Text(
                        text = "Kartik",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 25.sp,
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.ExtraBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            // Notification Button
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.notification),
                    contentDescription = "Notification Icon",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        // Start workout Card
        Card(
            shape = RoundedCornerShape(15),
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15))
                    .padding(top = 24.dp, bottom = 16.dp)
                    .clickable {},/*TODO() add the clicking functionality of navigation to exercise*/
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh)
        ) {
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                // decorative icon
                Box(
                    modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceContainerLow),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.exercise),
                        contentDescription = "Card Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(50.dp)
                    )
                }
                // Text part
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f).padding(8.dp)
                ) {
                    Text(
                        text = "Hey, Ready to Workout",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                    )
                    Text(
                        text = "Let's have a quick one",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                // Forward icon
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.baseline_arrow_forward_24),
                    contentDescription = "Forward Arrow"
                )
            }
        }

        Text(
            text = "Health Metrics",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HealthMetricCard(
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    displayText = "Cal Burned",
                    displayValue = 120f,
                    displayUnit = "cal",
                    displayIcon = painterResource(R.drawable.calories_svgrepo_com),
                )
                HealthMetricCard(
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    displayText = "Weight",
                    displayValue = 70f,
                    displayUnit = "Kg",
                    displayIcon = painterResource(R.drawable.weight),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HealthMetricCard(
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    displayText = "BMI",
                    displayValue = 19f,
                    displayUnit = "Kg/m\u00B2",
                    displayIcon = painterResource(R.drawable.speedometer),
                )
                HealthMetricCard(
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    displayText = "Height",
                    displayValue = 170f,
                    displayUnit = "cm",
                    displayIcon = painterResource(R.drawable.height),
                )
            }
        }

        Text(
            text = "Your Activity",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )

        ActivityGraph(modifier = Modifier.fillMaxWidth().padding(16.dp).height(200.dp))

    }
}

@Preview
@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    SweatOutTheme {
        HomeScreen(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface))
    }
}