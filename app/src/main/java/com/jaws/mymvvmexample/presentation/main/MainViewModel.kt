package com.jaws.mymvvmexample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private val _counter: MutableLiveData<Int> = MutableLiveData(0)

    val counter: LiveData<Int>
        get() = _counter

    private val _price: MutableLiveData<Int> = MutableLiveData(0)

    val price: LiveData<Int>
        get() = _price

    fun incrementCount(){
        val currentCount = _counter.value
        val newCount = (currentCount ?: 0) + 1
        _counter.postValue(newCount)

        val totalPrice = newCount * 18000
        _price.postValue(totalPrice)
    }

    fun decrementCount(){
        val currentCount = _counter.value
        val newCount = (currentCount ?: 0) - 1
        _counter.postValue(newCount)

        val totalPrice = newCount * 18000
        _price.postValue(totalPrice)
    }
}