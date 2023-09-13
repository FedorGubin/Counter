package com.example.counter

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MyViewModel : ViewModel() {
    private val _isButtonsEnabledLiveData = MutableLiveData<Boolean>(true)
    val isButtonsEnabledLiveData: LiveData<Boolean> = _isButtonsEnabledLiveData
    private val _counterLiveData = MutableLiveData<Int>(0)
    val counterLiveData: LiveData<Int> = _counterLiveData
    private val _newColorLiveData = MutableLiveData<Int>()
    val newColorLiveData: LiveData<Int> = _newColorLiveData
    fun toggleButtonState() {
        _isButtonsEnabledLiveData.value = !_isButtonsEnabledLiveData.value!!
    }

    fun incrementCounter() {
        _counterLiveData.value = _counterLiveData.value!! + 1
    }

    fun decrementCounter() {
        _counterLiveData.value = _counterLiveData.value!! - 1
    }

    fun toggleButtonColor() {
        _newColorLiveData.value = randomColor()
    }

    private fun randomColor(): Int {
        val r = Random.nextInt(255)
        val g = Random.nextInt(255)
        val b = Random.nextInt(255)
        return Color.rgb(r, g, b)
    }
}