package com.example.sweatout.welcome.presentation.goal_selection.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable
import com.example.sweatout.welcome.presentation.models.Goal

@Composable
fun GoalSelectionButton(
    modifier: Modifier = Modifier,
    goal: Goal? = null
) {
    MyAppButton(
        modifier = modifier
                .padding(vertical = 8.dp)
                .noRippleClickable { },
        onClick = {
            if (goal != null)
                goal.isChecked.value = ! goal.isChecked.value
        },
        style = MaterialTheme.typography.titleMedium,
        buttonColor = MaterialTheme.colorScheme.surfaceContainer,
        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
        buttonText = goal?.text ?: "",
        roundRadius = 20,
        elevation = if (goal?.isChecked?.value == true) dimensionResource(R.dimen.goal_select_button_elevation)
        else dimensionResource(
            R.dimen._0dp
        ),
        border = if (goal?.isChecked?.value == true) BorderStroke(
            width = dimensionResource(R.dimen.goal_select_button_border_width),
            color = MaterialTheme.colorScheme.primaryContainer
        )
        else null
    )
}
