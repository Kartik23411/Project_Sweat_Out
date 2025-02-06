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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sweatout.R

@Composable
fun WelcomeNavigationButtonRow(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onProceed: () -> Unit
) {
    Row(
        modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
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
                    .height(50.dp)
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
                    .height(50.dp)
                    .weight(1f)
                    .noRippleClickable {}
        )
    }
}

//Row(
//modifier = Modifier
//.fillMaxWidth()
//.padding(horizontal = 24.dp, vertical = 16.dp),
//horizontalArrangement = Arrangement.SpaceBetween
//) {
//    MyAppButton(
//        onClick = {
//            Log.e("activity Level", "${viewModel.userUiState.value.activityLevel}")
//            onCancelClick()
//        },
//        buttonColor = MaterialTheme.colorScheme.surfaceContainerHigh,
//        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
//        buttonText = stringResource(R.string.back),
//        modifier = Modifier
//                .weight(1f)
//                .height(50.dp)
//                .noRippleClickable { onCancelClick() }
//    )
//    Spacer(Modifier.width(24.dp))
//
//    MyAppButton(
//        onClick = {
//            viewModel.updateActivityLevel(
//                getActivityLevel(
//                    context, isSelected1,
//                    isSelected2, isSelected3
//                )
//            )
//            onProceedClick()
//        },
//        buttonText = stringResource(R.string.proceed),
//        modifier = Modifier
//                .weight(1f)
//                .height(50.dp)
//                .noRippleClickable { onProceedClick() }
//    )
//}