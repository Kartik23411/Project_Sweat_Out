package com.example.sweatout.welcome.presentation.activity_selection_screen.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable

@Composable
fun CustomActivityLevelButton(
    onClick: () -> Unit,
    isSelected: Boolean,
    @StringRes id: Int,
    modifier: Modifier = Modifier,
) {
    MyAppButton(
        onClick = { onClick() },
        buttonText = stringResource(id),
        buttonColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surfaceContainer,
        textColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
        else
            MaterialTheme.colorScheme.onSurfaceVariant,
        roundRadius = 20,
        modifier = modifier
                .padding(vertical = dimensionResource(R.dimen.activity_level_button_padding))
                .height(dimensionResource(R.dimen.activity_level_button_height))
                .shadow(
                    if (isSelected) dimensionResource(R.dimen.activity_level_button_shadow) else dimensionResource(R.dimen._0dp),
                    shape = RoundedCornerShape(20),
                    ambientColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = .3f)
                )
                .fillMaxWidth(1f)
                .noRippleClickable {},
        elevation = if (isSelected) dimensionResource(R.dimen.activity_level_button_shadow) else dimensionResource(R.dimen._0dp)
    )
}