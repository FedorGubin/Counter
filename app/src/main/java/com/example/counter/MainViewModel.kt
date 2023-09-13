package com.example.counter

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var isButtonsEnabled: Boolean = true
    var counter: Int = 0


    val newCounter = ValueObserver<Int>()

    fun test() {
        newCounter.addObserver(object : ObserverValue<Int> {
            override fun newValue(newValue: Int) {
                println(newValue)
            }
        })
    }

    fun test3() {
        newCounter.addObserver(object : ObserverValue<Int> {
            override fun newValue(newValue: Int) {
                println(newValue)
            }
        })
    }

    fun test2() {
        newCounter.setNewValue(300)
    }

    fun test4() {
        newCounter.setNewValue(500)
    }
}


class ValueObserver<T> {

    private var value: T? = null
    private val observers: MutableList<ObserverValue<T>> = mutableListOf()

    fun addObserver(newObserver: ObserverValue<T>) {
        observers.add(newObserver)
    }

    fun removeObserver(observer: ObserverValue<T>) {
        observers.remove(observer)
    }

    fun setNewValue(newValue: T) {
        value = newValue
        observers.forEach {
            it.newValue(newValue = newValue)
        }
    }
}

interface ObserverValue<T> {
    fun newValue(newValue: T)
}


