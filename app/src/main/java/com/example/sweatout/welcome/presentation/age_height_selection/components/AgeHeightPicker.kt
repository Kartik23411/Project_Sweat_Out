package com.example.sweatout.welcome.presentation.age_height_selection.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sweatout.welcome.presentation.components.DefaultWheelPickerDisplay
import com.example.sweatout.welcome.presentation.components.FWheelPickerContentScope
import com.example.sweatout.welcome.presentation.components.FWheelPickerDisplayScope
import com.example.sweatout.welcome.presentation.components.FWheelPickerState
import com.example.sweatout.welcome.presentation.components.WheelPicker
import com.example.sweatout.welcome.presentation.components.rememberFWheelPickerState

@Composable
fun Age_HeightPicker(
    modifier: Modifier = Modifier,
    count: Int,
    initialIndex:Int,
    state: FWheelPickerState = rememberFWheelPickerState(),
    key: ((index: Int) -> Any)? = null,
    itemHeight: Dp = 90.dp,
    unfocusedCount: Int = 2,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    focus: @Composable () -> Unit = { AgeHeightPickerDefaultFocus() },
    display: @Composable FWheelPickerDisplayScope.(index: Int) -> Unit = { DefaultWheelPickerDisplay(it) },
    content: @Composable FWheelPickerContentScope.(index: Int) -> Unit,
) {
    WheelPicker(
        modifier = modifier,
        isVertical = true,
        count = count,
        initialIndex = initialIndex,
        state = state,
        key = key,
        itemSize = itemHeight,
        unfocusedCount = unfocusedCount,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        focus = focus,
        display = display,
        content = content,
    )
}

@Composable
fun AgeHeightPickerDefaultFocus(
    modifier: Modifier = Modifier,
    dividerSize: Dp = 4.dp,
    dividerColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                    .background(dividerColor)
                    .height(dividerSize)
                    .fillMaxWidth(.5f)
                    .align(Alignment.TopCenter),
        )
        Box(
            modifier = Modifier
                    .background(dividerColor)
                    .height(dividerSize)
                    .fillMaxWidth(.5f)
                    .align(Alignment.BottomCenter),
        )
    }
}