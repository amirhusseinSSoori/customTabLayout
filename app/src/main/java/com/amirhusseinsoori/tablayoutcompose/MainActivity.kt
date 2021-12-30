package com.amirhusseinsoori.tablayoutcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.amirhusseinsoori.tablayoutcompose.component.*
import com.amirhusseinsoori.tablayoutcompose.ui.screen.one.ScreenOne
import com.amirhusseinsoori.tablayoutcompose.ui.screen.ScreenThree
import com.amirhusseinsoori.tablayoutcompose.ui.screen.two.ScreenTwo
import com.amirhusseinsoori.tablayoutcompose.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            TabLayoutComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {


                        TabLayoutAnimation()
                    }

            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabLayoutAnimation() {
    val pagerState = rememberPagerState(pageCount = 3)
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)

    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState, dragEnabled = false) { page ->
        when (page) {
            0 -> ScreenOne()
            1 -> ScreenTwo()
            2 -> ScreenThree()
        }
    }
}







