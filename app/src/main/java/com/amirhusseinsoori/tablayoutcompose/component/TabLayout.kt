package com.amirhusseinsoori.tablayoutcompose.component

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.amirhusseinsoori.tablayoutcompose.ui.theme.CompleteColor

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.math.roundToInt

val list = listOf("One", "Two", "Three")

@ExperimentalPagerApi
@Composable
fun TabLayoutIndicator(
    tabPositions: List<TabPosition>,
    tabPage: TabPage,
    list: List<String> = listOf("One", "Two", "Three"),
    index: Int

) {
    val transition = updateTransition(
        tabPage, label = "Tab Indicator"
    )
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            if (TabPage.Accept isTransitioningTo TabPage.InProgress ||
                TabPage.Accept isTransitioningTo TabPage.Complete ||
                TabPage.InProgress isTransitioningTo TabPage.Complete
            ) {
                spring(stiffness = Spring.StiffnessVeryLow)
            } else {
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = "Indicator Left"
    ) { page ->
        tabPositions[page.ordinal].left
    }

    val indicatorRight by transition.animateDp(
        transitionSpec = {
            if (TabPage.Accept isTransitioningTo TabPage.InProgress ||
                TabPage.Accept isTransitioningTo TabPage.Complete ||
                TabPage.InProgress isTransitioningTo TabPage.Complete
            ) {
                spring(stiffness = Spring.StiffnessMedium)
            } else {
                spring(stiffness = Spring.StiffnessVeryLow)
            }
        },
        label = "Indicator Right"
    ) { page ->
        tabPositions[page.ordinal].right
    }
    val color by transition.animateColor(
        label = "Border Color"
    ) { page ->
        when (page) {
            TabPage.Accept -> Color.Yellow
            else -> Color.Yellow
        }
    }

    Column(
        modifier = Modifier
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, color), RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(list[index])
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(0) }
    TabRow(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 25.dp),
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabLayoutIndicator(
                tabPositions = tabPositions,
                tabPage =
                when (pagerState.currentPage) {
                    0 -> TabPage.Accept
                    1 -> TabPage.InProgress
                    else -> TabPage.Complete
                },
                index = expanded
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                modifier = Modifier
                    .background(Color.White)
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        BorderStroke(2.dp, Color.Black),
                        RoundedCornerShape(20.dp),

                        )
                    .background(CompleteColor)
                    .clip(RoundedCornerShape(10.dp)),
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                        expanded = index
                    }
                },
                text = {

                    Text(
                        text = list[index],
                        color = if (pagerState.currentPage == index) Color.Black else Color.Black.copy(
                            alpha = 0.5f
                        ),
                        fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )


        }
    }
}

const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

enum class TabPage {
    Accept, InProgress, Complete
}