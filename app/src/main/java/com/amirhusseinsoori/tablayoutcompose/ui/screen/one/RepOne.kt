package com.amirhusseinsoori.tablayoutcompose.ui.screen.one

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepOne @Inject constructor() {


    fun start(): Flow<Details> = flow {
        var a = 4
        while (a >= 0) {

            delay(2000)

            if (a == 0) {
                emit(Details(a, true))
            }else{
                emit(Details(a, true))
            }
            a--

        }




    }.flowOn(Dispatchers.IO)

}