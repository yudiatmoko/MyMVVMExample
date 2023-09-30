package com.jaws.mymvvmexample.presentation.data.local

import androidx.datastore.preferences.core.intPreferencesKey
import com.jaws.mymvvmexample.presentation.utils.PreferenceDataStoreHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first


interface UserPreferenceDataSource {
    fun getUserCounterPrefFlow(): Flow<Int>
    suspend fun getUserCounterPref(): Int
    suspend fun setUserCounterPref(counterValue : Int)

    fun getUserPricePrefFlow(): Flow<Int>
    suspend fun getUserPricePref(): Int
    suspend fun setUserPricePref(priceValue : Int)

    suspend fun increment()
    suspend fun decrement()
}

class UserPreferenceDataSourceImpl(
    private val preferenceDataStoreHelper: PreferenceDataStoreHelper
):UserPreferenceDataSource{

    override fun getUserCounterPrefFlow(): Flow<Int> {
        return preferenceDataStoreHelper.getPreference(
            PREF_COUNTER_VALUE, 0
        )
    }

    override suspend fun getUserCounterPref(): Int {
        return preferenceDataStoreHelper.getFirstPreference(
            PREF_COUNTER_VALUE,0)
    }

    override suspend fun setUserCounterPref(
        counterValue: Int,
    ) {
        preferenceDataStoreHelper.putPreference(
            PREF_COUNTER_VALUE, counterValue)
    }

    override fun getUserPricePrefFlow(): Flow<Int> {
        return preferenceDataStoreHelper.getPreference(
            PREF_PRICE_VALUE, 0
        )
    }

    override suspend fun getUserPricePref(): Int {
        return preferenceDataStoreHelper.getFirstPreference(
            PREF_PRICE_VALUE,0)
    }

    override suspend fun setUserPricePref(
        priceValue: Int,
    ) {
        preferenceDataStoreHelper.putPreference(
            PREF_PRICE_VALUE, priceValue)
    }

    override suspend fun increment() {
        val currentCount = getUserCounterPref()
        val newCount = currentCount + 1
        val totalPrice = newCount * 18000
        setUserCounterPref(newCount)
        setUserPricePref(totalPrice)
    }

    override suspend fun decrement() {
        val currentCount = getUserCounterPref()
        val newCount = currentCount - 1
        if (newCount < 0) return
        val totalPrice = newCount * 18000
        setUserCounterPref(newCount)
        setUserPricePref(totalPrice)
    }

    companion object{
        val PREF_COUNTER_VALUE = intPreferencesKey("PREF_COUNTER_VALUE")
        val PREF_PRICE_VALUE = intPreferencesKey("PREF_PRICE__VALUE")
    }
}