package com.amirhusseinsoori.tablayoutcompose.ui.screen.one

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class OneViewModel @Inject constructor(val rep: RepOne) : ViewModel() {

    val state = MutableStateFlow(Details())
    val _state = state.asStateFlow()

    init {
        start()
    }

    @FlowPreview
    fun start() {
        viewModelScope.launch {
            rep.start().debounce { 1000 }
            .collect {
                state.value = it
            }
        }


    }
}

data class Details(var str: Int = 0, var b: Boolean = true)