package com.jaws.mymvvmexample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jaws.mymvvmexample.presentation.data.local.UserPreferenceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    val dataSource: UserPreferenceDataSource
) : ViewModel(){

//    private val _price: MutableLiveData<Int> = MutableLiveData(0)
//    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
//
    val counter: LiveData<Int>
        get() = dataSource.getUserCounterPrefFlow().asLiveData(Dispatchers.Main)

    val price: LiveData<Int>
        get() =  dataSource.getUserPricePrefFlow().asLiveData(Dispatchers.Main)

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