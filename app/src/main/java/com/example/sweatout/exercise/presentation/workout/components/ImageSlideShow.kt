package com.example.sweatout.exercise.presentation.workout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sweatout.R
import com.example.sweatout.ui.theme.SweatOutTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun ImageSlide(modifier: Modifier = Modifier) {

    val images = listOf(
        R.drawable.arm_circles_exercise_illustration,
        R.drawable.arnold_shoulder_press_exercise_illustration_spotebi,
        R.drawable.push_up_exercise_illustration,
        R.drawable.mountain_climbers_exercise_illustration
    )

    val pagerState = rememberPagerState(pageCount = { images.size }, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val next = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(next)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    coroutineScope.launch {
                        if (dragAmount > 0) {
                            pagerState.animateScrollToPage(
                                (pagerState.currentPage - 1).coerceAtLeast(0)
                            )
                        }
                        else {
                            pagerState.animateScrollToPage(
                                (pagerState.currentPage + 1).coerceAtMost(images.size - 1)
                            )
                        }
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 60.dp),
            pageSpacing = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val scale = 1f - (.2f * abs(pageOffset))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            ) {
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp)
                        .border(
                            width = .5.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(10)
                        )
                        .clip(RoundedCornerShape(10)),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewImageSlideShow() {
    SweatOutTheme {
        ImageSlide()
    }
}