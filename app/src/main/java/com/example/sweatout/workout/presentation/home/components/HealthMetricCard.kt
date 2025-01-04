package com.example.sweatout.workout.presentation.home.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sweatout.R
import com.example.sweatout.ui.theme.SweatOutTheme
import java.text.NumberFormat
import java.util.Locale

@Composable
fun HealthMetricCard(
    modifier: Modifier = Modifier,
    displayText: String,
    displayValue: Float,
    displayUnit: String,
    displayIcon: Painter
) {
    Card(
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = RoundedCornerShape(15)
    ) {
        // Name and Icon Row
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 4.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = displayText,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Light),
                color = MaterialTheme.colorScheme.onSurface
            )
            Icon(
                painter = displayIcon,
                contentDescription = "Display Icon",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = .75f),
                modifier = Modifier.size(35.dp)
            )
        }
        // value and Unit Row
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, bottom = 12.dp),
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
    }
}

fun Float.formattedString():String{
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
            "cal", painterResource(R.drawable.calories_svgrepo_com)
        )
    }
}