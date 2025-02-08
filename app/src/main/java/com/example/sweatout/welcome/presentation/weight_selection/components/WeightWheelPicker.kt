package com.example.sweatout.welcome.presentation.weight_selection.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.components.DefaultWheelPickerDisplay
import com.example.sweatout.welcome.presentation.components.FWheelPickerContentScope
import com.example.sweatout.welcome.presentation.components.FWheelPickerDisplayScope
import com.example.sweatout.welcome.presentation.components.FWheelPickerState
import com.example.sweatout.welcome.presentation.components.WheelPicker
import com.example.sweatout.welcome.presentation.components.rememberFWheelPickerState

@Composable
fun WeightWheelPicker(
    modifier: Modifier = Modifier,
    count: Int,
    initialIndex: Int,
    state: FWheelPickerState = rememberFWheelPickerState(),
    key: ((index: Int) -> Any)? = null,
    itemWidth: Dp = 90.dp,
    unfocusedCount: Int = 1,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    focus: @Composable () -> Unit = { WeightWheelPickerFocus() },
    display: @Composable FWheelPickerDisplayScope.(index: Int) -> Unit = {
        DefaultWheelPickerDisplay(
            it
        )
    },
    content: @Composable FWheelPickerContentScope.(index: Int) -> Unit,
) {
    WheelPicker(
        modifier = modifier,
        isVertical = false,
        count = count,
        initialIndex = initialIndex,
        state = state,
        key = key,
        itemSize = itemWidth,
        unfocusedCount = unfocusedCount,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        focus = focus,
        display = display,
        content = content,
    )
}

@Composable
fun WeightWheelPickerFocus() {
    Column(
        modifier = Modifier
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(100.dp))
        Icon(
            modifier = Modifier.size(90.dp),
            painter = painterResource(R.drawable.baseline_arrow_drop_up_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}