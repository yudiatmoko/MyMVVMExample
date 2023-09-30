package com.jaws.mymvvmexample.presentation.data.local

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.appDataStore by preferencesDataStore(
    name = "MyMVVMExample"
)