package com.jaws.mymvvmexample.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace.setCounter
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.jaws.mymvvmexample.R
import com.jaws.mymvvmexample.databinding.ActivityMainBinding
import com.jaws.mymvvmexample.presentation.data.CounterDataSource
import com.jaws.mymvvmexample.presentation.data.CounterDataSourceImpl
import com.jaws.mymvvmexample.presentation.utils.GenericViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

//    private val viewModel: MainViewModel by viewModels()

    private val viewModel: MainViewModel by viewModels {
        val dataSource: CounterDataSource = CounterDataSourceImpl()
        GenericViewModelFactory.create(MainViewModel(dataSource))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setCounterValue()
    }

    private fun setCounterValue() {

        binding.btnIncrement.setOnClickListener{
            increment()
        }

        binding.btnDecrement.setOnClickListener{
            decrement()
        }

        viewModel.counter.observe(this){
            binding.tvCounter.text = it.toString()
        }

        viewModel.price.observe(this){
            binding.tvTotal.text = String.format(
                getString(
                    R.string.idr
                ), it.toDouble())
        }
    }

    private fun increment(){
        viewModel.incrementCount()
    }

    private fun decrement(){
        viewModel.decrementCount()
    }
}