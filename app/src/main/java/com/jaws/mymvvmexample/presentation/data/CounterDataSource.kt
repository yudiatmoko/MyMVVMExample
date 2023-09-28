package com.jaws.mymvvmexample.presentation.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

interface CounterDataSource {
    fun getCounterFlow(): Flow<Int>
//    fun getPriceFlow(): Flow<Int>

    val priceFlow: Flow<Int>
    suspend fun increment()
    suspend fun decrement()
}

class CounterDataSourceImpl : CounterDataSource{

    private val counterFlow = MutableStateFlow(0)

//    private val priceFlow = MutableStateFlow(0)
//    override fun getPriceFlow(): Flow<Int> = priceFlow

    private val _priceFlow = MutableStateFlow(0)

    override fun getCounterFlow(): Flow<Int> = counterFlow
    override val priceFlow: Flow<Int>
        get() = _priceFlow

    override suspend fun increment() {
        val currentCount = counterFlow.first()
        val newCount = currentCount + 1
        val totalPrice = newCount * 18000
        counterFlow.emit(newCount)
        _priceFlow.emit(totalPrice)
    }

    override suspend fun decrement() {
        val currentCount = counterFlow.first()
        val newCount = currentCount - 1
        val totalPrice = newCount * 18000
        counterFlow.emit(newCount)
        _priceFlow.emit(totalPrice)
    }
}