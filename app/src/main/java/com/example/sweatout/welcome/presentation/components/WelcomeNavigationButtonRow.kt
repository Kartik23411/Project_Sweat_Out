package com.example.sweatout.welcome.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.core.presentation.MyAppButton
import com.example.sweatout.core.presentation.noRippleClickable

@Composable
fun WelcomeNavigationButtonRow(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onProceed: () -> Unit
) {
    Row(
        modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(R.dimen.welcome_module_navigation_button_horizontal_padding),
                    vertical = dimensionResource(R.dimen.welcome_module_navigation_button_vertical_padding)
                ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MyAppButton(
            onClick = {
                onCancel()
            },
            buttonText = stringResource(R.string.back),
            buttonColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                    .height(dimensionResource(R.dimen.welcome_module_navigation_button_height))
                    .weight(1f)
                    .noRippleClickable {}
        )
        Spacer(
            modifier = Modifier.width(24.dp)
        )
        MyAppButton(
            onClick = {
                onProceed()
            },
            buttonText = stringResource(R.string.proceed),
            modifier = Modifier
                    .height(dimensionResource(R.dimen.welcome_module_navigation_button_height))
                    .weight(1f)
                    .noRippleClickable {}
        )
    }
}
