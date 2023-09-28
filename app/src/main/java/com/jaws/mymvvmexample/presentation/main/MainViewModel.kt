package com.jaws.mymvvmexample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jaws.mymvvmexample.presentation.data.CounterDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    val dataSource: CounterDataSource
) : ViewModel(){

//    private val _price: MutableLiveData<Int> = MutableLiveData(0)
//    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
//
    val counter: LiveData<Int>
        get() = dataSource.getCounterFlow().asLiveData(Dispatchers.Main)

    val price: LiveData<Int>
        get() =  dataSource.priceFlow.asLiveData(Dispatchers.Main)

    fun incrementCount(){
        viewModelScope.launch {
            dataSource.increment()
        }
    }

    fun decrementCount(){
        viewModelScope.launch {
            dataSource.decrement()
        }
    }
}