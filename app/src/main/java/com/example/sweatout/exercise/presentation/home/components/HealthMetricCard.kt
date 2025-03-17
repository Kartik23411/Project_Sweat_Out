package com.example.sweatout.exercise.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sweatout.R
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.ui.theme.SweatOutTheme
import java.text.NumberFormat
import java.util.Locale

@Composable
fun HealthMetricCard(
    modifier: Modifier = Modifier,
    displayText: String,
    displayValue: Float,
    displayUnit: String,
    displayIcon: Painter,
    isExpanded: Boolean,
    onCardClick: () -> Unit,
    cardIndex: Int,
    expandedContent: @Composable () -> Unit = { DefaultExpandedContent() }
) {
    val localDensity = LocalDensity.current
    val elevationDp = if (isExpanded) 12.dp else 1.dp

    val cardHeight by animateFloatAsState(
        targetValue = if (isExpanded) 200f else 100f,
        animationSpec = tween(durationMillis = 300),
        label = "Card Height"
    )

    val enterTransition = when (cardIndex) {
        0    -> expandHorizontally(expandFrom = Alignment.End) + expandVertically(expandFrom = Alignment.Bottom) // bottom end
        1    -> expandHorizontally(expandFrom = Alignment.Start) + expandVertically(expandFrom = Alignment.Bottom) // bottom start
        2    -> expandHorizontally(expandFrom = Alignment.End) + expandVertically(expandFrom = Alignment.Top) // top end
        3    -> expandHorizontally(expandFrom = Alignment.Start) + expandVertically(expandFrom = Alignment.Top) // top start
        else -> expandHorizontally() + expandVertically()
    }

    val exitTransition = when (cardIndex) {
        0    -> shrinkHorizontally(shrinkTowards = Alignment.End) + shrinkVertically(shrinkTowards = Alignment.Bottom)
        1    -> shrinkHorizontally(shrinkTowards = Alignment.Start) + shrinkVertically(shrinkTowards = Alignment.Bottom)
        2    -> shrinkHorizontally(shrinkTowards = Alignment.End) + shrinkVertically(shrinkTowards = Alignment.Top)
        3    -> shrinkHorizontally(shrinkTowards = Alignment.Start) + shrinkVertically(shrinkTowards = Alignment.Top)
        else -> shrinkHorizontally() + shrinkVertically()
    }

    Card(
        modifier = modifier
                .fillMaxWidth()
                .height(cardHeight.dp)
                .graphicsLayer {
                    shadowElevation = with(localDensity) { elevationDp.toPx() }
                }
                .zIndex(if (isExpanded) 10f else 1f)  // For ensuring that the expanded card will
                // be shown on the top
                .noRippleClickable { onCardClick() },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = RoundedCornerShape(15),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevationDp
        )
    ) {
        // Name and Icon Row
        if (! isExpanded)
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 4.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = displayText,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    painter = displayIcon,
                    contentDescription = "Display Icon",
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = .75f),
                    modifier = Modifier.size(35.dp)
                )
            }

        // Value and Unit Row
        if (! isExpanded)
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = if (isExpanded) 0.dp else 12.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = displayValue.formattedString(),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Box(
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = displayUnit,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .7f)
                        )
                    }
                )
            }

        // Expanded content
        AnimatedVisibility(
            visible = isExpanded,
            enter = enterTransition.plus(
                fadeIn(
                    animationSpec = tween(
                        800,
                        500,
                        FastOutLinearInEasing
                    )
                )
            ),
            exit = exitTransition.plus(
                fadeOut(
                    animationSpec = tween(
                        800,
                        500,
                        easing = FastOutSlowInEasing
                    )
                )
            )
        ) {
            Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
            ) {
                expandedContent()
            }
        }
    }
}

@Composable
private fun DefaultExpandedContent() {
    Text(
        text = "Expanded card content goes here. You can customize this with charts, additional metrics, or other information.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun DefaultBMICardContent(modifier: Modifier = Modifier, bmiCategory: String) {
    Text(
        modifier = modifier.padding(start = 16.dp, top = 16.dp),
        text = bmiCategory,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

fun Float.formattedString(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 1
        minimumFractionDigits = 0
    }
    return formatter.format(this)
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun HealthMetricCardPreview() {
    SweatOutTheme {
        HealthMetricCard(
            Modifier, "Blood Pressure", (160.1).toFloat(),
            "cal", painterResource(R.drawable.calories_svgrepo_com), false, {}, 1
        )
    }
}