package com.amirhusseinsoori.tablayoutcompose.ui.screen.one

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ScreenOne() {
    val viewModel: OneViewModel = hiltViewModel()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        viewModel._state.collectAsState().let {
            Text(
                text = it.value.str.toString(),
                style = MaterialTheme.typography.h5,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Button(enabled = it.value.b,onClick = { viewModel.start()}) {

            }
        }


    }
}


