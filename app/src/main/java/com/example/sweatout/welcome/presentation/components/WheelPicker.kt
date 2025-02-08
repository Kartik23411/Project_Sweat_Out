package com.example.sweatout.welcome.presentation.components

import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp

interface FWheelPickerContentScope {
    val state: FWheelPickerState
}

interface FWheelPickerDisplayScope : FWheelPickerContentScope {
    @Composable
    fun Content(index: Int)
}

@Composable
fun WheelPicker(
    modifier: Modifier,
    isVertical: Boolean,
    count: Int,
    initialIndex: Int,
    state: FWheelPickerState,
    key: ((index: Int) -> Any)?,
    itemSize: Dp,
    unfocusedCount: Int,
    userScrollEnabled: Boolean,
    reverseLayout: Boolean,
    focus: @Composable () -> Unit,
    display: @Composable FWheelPickerDisplayScope.(index: Int) -> Unit,
    content: @Composable FWheelPickerContentScope.(index: Int) -> Unit,
) {
    require(count >= 0) { "Require count >= 0" }
    require(unfocusedCount >= 0) { "Require unfocusedCount >= 0" }
    require(itemSize > 0.dp) { "Require itemSize > 0.dp" }

    SafeBox(
        modifier = modifier,
        isVertical = isVertical,
        itemSize = itemSize,
        unfocusedCount = unfocusedCount,
    ) {
        InternalWheelPicker(
            isVertical = isVertical,
            count = count,
            initialIndex = initialIndex,
            state = state,
            key = key,
            itemSize = itemSize,
            unfocusedCount = it,
            userScrollEnabled = userScrollEnabled,
            reverseLayout = reverseLayout,
            focus = focus,
            display = display,
            content = content,
        )

        // if statement deleted
    }
}

@Composable
private fun InternalWheelPicker(
    isVertical: Boolean,
    count: Int,
    initialIndex: Int,
    state: FWheelPickerState,
    key: ((index: Int) -> Any)?,
    itemSize: Dp,
    unfocusedCount: Int,
    userScrollEnabled: Boolean,
    reverseLayout: Boolean,
    focus: @Composable () -> Unit,
    display: @Composable FWheelPickerDisplayScope.(index: Int) -> Unit,
    content: @Composable FWheelPickerContentScope.(index: Int) -> Unit,
) {
    LaunchedEffect(state, count) {
        state.updateCount(count)
    }
    LaunchedEffect(Unit) {
        state.animateScrollToIndex(initialIndex)
    }

    val nestedScrollConnection = remember(state) {
        WheelPickerNestedScrollConnection(state)
    }.apply {
        this.isVertical = isVertical
        this.itemSizePx = with(LocalDensity.current) { itemSize.roundToPx() }
        this.reverseLayout = reverseLayout
    }

    val totalSize = remember(itemSize, unfocusedCount) {
        itemSize * (unfocusedCount * 2 + 1)
    }

    val displayScope = remember(state) {
        FWheelPickerDisplayScopeImpl(state)
    }.apply {
        this.content = content
    }

    Box(
        modifier = Modifier
                .nestedScroll(nestedScrollConnection)
                .graphicsLayer {
                    this.alpha = if (state.isReady) 1f else 0f
                }
                .run {
                    if (totalSize > 0.dp) {
                        if (isVertical) {
                            height(totalSize).widthIn(40.dp)
                        }
                        else {
                            width(totalSize).heightIn(40.dp)
                        }
                    }
                    else {
                        this
                    }
                },
        contentAlignment = Alignment.Center,
    ) {

        val lazyListScope: LazyListScope.() -> Unit =
                { // here dot signifies that the lambda operates on Lazy list scope

                    repeat(unfocusedCount) {
                        item(contentType = "placeholder") {
                            ItemSizeBox(
                                isVertical = isVertical,
                                itemSize = itemSize,
                            )
                        }
                    }

                    items(
                        count = count,
                        key = key,
                    ) { index ->
                        ItemSizeBox(
                            isVertical = isVertical,
                            itemSize = itemSize,
                        ) {
                            displayScope.display(index)
                        }
                    }

                    repeat(unfocusedCount) {
                        item(contentType = "placeholder") {
                            ItemSizeBox(
                                isVertical = isVertical,
                                itemSize = itemSize,
                            )
                        }
                    }
                }

        if (isVertical) {
            LazyColumn(
                state = state.lazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                reverseLayout = reverseLayout,
                userScrollEnabled = userScrollEnabled && state.isReady,
                modifier = Modifier.matchParentSize(),
                content = lazyListScope,
            )
        }
        else {
            LazyRow(
                state = state.lazyListState,
                verticalAlignment = Alignment.CenterVertically,
                reverseLayout = reverseLayout,
                userScrollEnabled = userScrollEnabled && state.isReady,
                modifier = Modifier.matchParentSize(),
                content = lazyListScope,
            )
        }

        ItemSizeBox(
            // it is the decorative box for the middle item
            modifier = Modifier.align(Alignment.Center),
            isVertical = isVertical,
            itemSize = itemSize,
        ) {
            focus()
        }
    }
}

@Composable  // This composable will make a box for the wheel picker of the correct shape.
private fun SafeBox(
    modifier: Modifier = Modifier,
    isVertical: Boolean,
    itemSize: Dp,
    unfocusedCount: Int,
    content: @Composable (safeUnfocusedCount: Int) -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val maxSize = if (isVertical) maxHeight else maxWidth
        val result = remember(maxSize, itemSize, unfocusedCount) {
            val totalSize = itemSize * (unfocusedCount * 2 + 1)
            if (totalSize <= maxSize) {
                unfocusedCount
            }
            else {
                (((maxSize - itemSize) / 2f) / itemSize).toInt().coerceAtLeast(0)
                // in this calculation the item_size subtracted is for the padding and the remaining space then divided by item_size to check how many items can be fitted up and then converted to int
            }
        }
        content(result)
    }
}

@Composable
private fun ItemSizeBox(
    // to make the box for item
    modifier: Modifier = Modifier,
    isVertical: Boolean,
    itemSize: Dp,
    content: @Composable () -> Unit = { },
) {
    Box(
        modifier
                .run {
                    if (isVertical) { //Calls the specified function block with this value as its receiver and returns its result.
                        height(itemSize)  // declare the preferred height in dp
                    }
                    else {
                        width(itemSize) // declare the preferred width if horizontal wheel picker
                    }
                },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

private class WheelPickerNestedScrollConnection(
    private val state: FWheelPickerState,
) : NestedScrollConnection { // as there exists nested scrollable content so we need it manage them
    var isVertical: Boolean = true
    var itemSizePx: Int = 0
    var reverseLayout: Boolean = false

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        state.synchronizeCurrentIndexSnapshot()
        return super.onPostScroll(consumed, available, source)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val currentIndex = state.synchronizeCurrentIndexSnapshot()
        return if (currentIndex >= 0) {
            available.flingItemCount(
                isVertical = isVertical,
                itemSize = itemSizePx,
                decay = exponentialDecay(5f),
                reverseLayout = reverseLayout,
            ).let { flingItemCount ->
                if (flingItemCount == 0) {
                    state.animateScrollToIndex(currentIndex)
                }
                else {
                    state.animateScrollToIndex(currentIndex - flingItemCount)
                }
            }
            available
        }
        else {
            super.onPreFling(available)
        }
    }
}

private fun Velocity.flingItemCount(
    isVertical: Boolean,
    itemSize: Int,
    decay: DecayAnimationSpec<Float>,
    reverseLayout: Boolean,
): Int {
    if (itemSize <= 0) return 0
    val velocity = if (isVertical) y else x
    val targetValue = decay.calculateTargetValue(0f, velocity)
    val flingItemCount = (targetValue / itemSize).toInt()
    return if (reverseLayout) - flingItemCount else flingItemCount
}

private class FWheelPickerDisplayScopeImpl(
    override val state: FWheelPickerState,
) : FWheelPickerDisplayScope {

    var content: @Composable FWheelPickerContentScope.(index: Int) -> Unit by mutableStateOf({})

    @Composable
    override fun Content(index: Int) {
        content(index)
    }
}