package com.example.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val _isButtonsEnabledLiveData = MutableLiveData<Boolean>(true)
    val isButtonsEnabledLiveData: LiveData<Boolean> = _isButtonsEnabledLiveData
    private val _counterLiveData = MutableLiveData<Int>(0)
    val counterLiveData: LiveData<Int> = _counterLiveData
    private val _newColorLiveData =  MutableLiveData<Int>()
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

    fun toggleButtonColor(newColor: Int) {
        _newColorLiveData.value = newColor
    }
}