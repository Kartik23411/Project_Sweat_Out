package com.example.sweatout.exercise.presentation.home

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sweatout.R
import com.example.sweatout.exercise.presentation.WorkoutViewModal
import com.example.sweatout.exercise.presentation.home.components.ActivityGraph
import com.example.sweatout.exercise.presentation.home.components.HealthMetricCard
import com.example.sweatout.exercise.presentation.home.components.SectionSeparationText
import com.example.sweatout.ui.theme.SweatOutTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onStartClick:()->Unit,
    onNotificationIconClick:()->Unit = {},
    viewModel: WorkoutViewModal = hiltViewModel()
) {
//    /todo implement the logic of changing of user profile image using Async image
//    todo implement the health matrix based on the user data
    val user by viewModel.userUi.collectAsState()
    var expandedCardIndex by rememberSaveable { mutableStateOf(-1) }

    val mainScrollState = rememberScrollState()

    Column(
        modifier = modifier
                .fillMaxSize()
                .verticalScroll(mainScrollState)
                .padding(horizontal = 16.dp),
    ) {
        // Top bar row
        TopAppBar(
            userName = user?.name ?: "user",
            onNotificationButtonClick = {onNotificationIconClick()},
            modifier = Modifier.fillMaxWidth()
        )

        // Start workout Card
        StartWorkoutCard(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15))
                .padding(top = 24.dp, bottom = 16.dp)
                .clickable { onStartClick() })

        SectionSeparationText(
            textId = R.string.health_metrics,
            modifier = Modifier.padding(12.dp)
        )

        // Health Stats - wrap in a fixed size box to prevent layout shifting
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (expandedCardIndex == -1) 240.dp else 220.dp)
        ) {
            Column(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
            ) {
                if (expandedCardIndex == -1 || expandedCardIndex == 0 || expandedCardIndex == 1){
                    Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        if (expandedCardIndex == -1 || expandedCardIndex == 0) {
                            HealthMetricCard(
                                modifier = Modifier
                                        .weight(1f)
                                        .padding(end = if (expandedCardIndex == - 1) 8.dp else 0.dp),
                                displayText = stringResource(R.string.cal_card),
                                displayValue = 120f,
                                displayUnit = stringResource(R.string.unit_cal),
                                isExpanded = expandedCardIndex == 0,
                                onCardClick = {
                                    expandedCardIndex = if (expandedCardIndex == 0) - 1 else 0
                                },
                                displayIcon = painterResource(R.drawable.calories_svgrepo_com),
                                cardIndex = 0  // Card 1: Expansion from bottom end
                            )
                        }
                        if (expandedCardIndex == -1 || expandedCardIndex == 1) {
                            HealthMetricCard(
                                modifier = Modifier
                                        .weight(1f)
                                        .padding(start = if (expandedCardIndex == - 1) 8.dp else 0.dp),
                                displayText = stringResource(R.string.weight_card),
                                displayValue = user?.weight?.toFloat() ?: 56f,
                                displayUnit = stringResource(R.string.unit_kg),
                                isExpanded = expandedCardIndex == 1,
                                onCardClick = {
                                    expandedCardIndex = if (expandedCardIndex == 1) - 1 else 1
                                },
                                displayIcon = painterResource(R.drawable.weight),
                                cardIndex = 1  // Card 2: Expansion from bottom start
                            )
                        }
                    }
                }

                if (expandedCardIndex == -1 || expandedCardIndex == 2 || expandedCardIndex == 3){
                    Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        if (expandedCardIndex == -1 || expandedCardIndex == 2) {
                            HealthMetricCard(
                                modifier = Modifier
                                        .weight(1f)
                                        .padding(end = if (expandedCardIndex == - 1) 8.dp else 0.dp),
                                displayText = stringResource(R.string.bmi_card),
                                displayValue = 19.38f,
                                displayUnit = stringResource(R.string.unit_bmi),
                                isExpanded = expandedCardIndex == 2,
                                onCardClick = {
                                    expandedCardIndex = if (expandedCardIndex == 2) - 1 else 2
                                },
                                displayIcon = painterResource(R.drawable.speedometer),
                                cardIndex = 2  // Card 3: Expansion from top end
                            )
                        }
                        if (expandedCardIndex == -1 || expandedCardIndex == 3) {
                            HealthMetricCard(
                                modifier = Modifier
                                        .weight(1f)
                                        .padding(start = if (expandedCardIndex == - 1) 8.dp else 0.dp),
                                displayText = stringResource(R.string.height_card),
                                displayValue = user?.height?.toFloat() ?: 170f,
                                displayUnit = stringResource(R.string.unit_cm),
                                isExpanded = expandedCardIndex == 3,
                                onCardClick = {
                                    expandedCardIndex = if (expandedCardIndex == 3) - 1 else 3
                                },
                                displayIcon = painterResource(R.drawable.height),
                                cardIndex = 3  // Card 4: Expansion from top start
                            )
                        }
                    }
                }
            }

        }

        SectionSeparationText(
            textId = R.string.your_activity,
            modifier = Modifier.padding(12.dp)
        )

        ActivityGraph(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 12.dp)
                    .height(200.dp)
        )

    }
}


@Composable
fun GreetingTextRow(
    userName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.welcome_back),
            style = (MaterialTheme.typography.titleSmall).copy(
                fontSize = 14.sp,
                lineHeight = 12.sp
            ),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .75f)
        )
        Text(
            text = userName,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 25.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.ExtraBold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun StartWorkoutCard(
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(15),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // decorative icon
            Box(
                modifier = Modifier
                        .size(78.dp)
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
                modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.start_exercise_card_text),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                )
                Text(
                    text = stringResource(R.string.start_exercise_card_text_2),
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            // Forward icon
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.baseline_arrow_forward_24),
                contentDescription = null
            )
        }
    }
}

@Composable
fun TopAppBar(
    userName: String,
    onNotificationButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile and Greeting Row
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            IconButton(
                modifier = Modifier.size(50.dp),
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.onSurface)
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.ic_launcher_foreground),
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
            GreetingTextRow(
                modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                userName = userName
            )
        }
        // Notification Button
        IconButton(
            onClick = {onNotificationButtonClick()}
        ) {
            Icon(
                painter = painterResource(R.drawable.notification),
                contentDescription = "Notification Icon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    SweatOutTheme {
        HomeScreen(
            modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface),
            {}
        )
    }
}