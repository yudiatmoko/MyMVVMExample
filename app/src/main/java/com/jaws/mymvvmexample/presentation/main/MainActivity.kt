package com.jaws.mymvvmexample.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jaws.mymvvmexample.R
import com.jaws.mymvvmexample.databinding.ActivityMainBinding
import com.jaws.mymvvmexample.presentation.data.local.appDataStore
import com.jaws.mymvvmexample.presentation.data.local.UserPreferenceDataSourceImpl
import com.jaws.mymvvmexample.presentation.utils.GenericViewModelFactory
import com.jaws.mymvvmexample.presentation.utils.PreferenceDataStoreHelperImpl

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

//    private val viewModel: MainViewModel by viewModels()

    private val viewModel: MainViewModel by viewModels {
        val dataStore = this.appDataStore
        val dataStoreHelper = PreferenceDataStoreHelperImpl(dataStore)
        val userPreferenceDataSource = UserPreferenceDataSourceImpl(dataStoreHelper)
        GenericViewModelFactory.create(MainViewModel(userPreferenceDataSource))
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

//        viewModel.counter.observe(this){
//            binding.tvCounter.text = it.toString()
//        }
//
//        viewModel.price.observe(this){
//            binding.tvTotal.text = String.format(
//                getString(
//                    R.string.idr
//                ), it.toDouble())
//        }

        viewModel.apply {
            getDataStoreCounter().observe(this@MainActivity){
                binding.tvCounter.text = it.toString()
            }

            counter.observe(this@MainActivity){
                binding.tvCounter.text = it.toString()
            }
            getDataStorePrice().observe(this@MainActivity){
                binding.tvTotal.text = it.toString()
            }

           price.observe(this@MainActivity){
                binding.tvTotal.text = it.toString()
            }
        }
    }

    private fun increment(){
        viewModel.incrementCount()
    }

    private fun decrement(){
        viewModel.decrementCount()
    }
}