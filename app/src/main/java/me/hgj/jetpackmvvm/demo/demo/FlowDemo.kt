package me.hgj.jetpackmvvm.demo.demo

import android.widget.EditText
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.ext.view.afterTextChange

/**
 * @user huanghongfu
 * @date 2020/11/2
 */

fun foo(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun test() {

    runBlocking {
        flow {
//            println("Flow started")
//            for (i in 1..3) {
                delay(100)
                emit(1)
//            }
        }
            .collect {
                println("collect ${Thread.currentThread().name}:emit: $it")
            }

    }
    println("2")

    EditText(appContext).afterTextChange {
        CoroutineScope(Dispatchers.IO).launch{
            mutableListOf(it).asFlow().debounce(100).collect{

            }
        }

    }

}

