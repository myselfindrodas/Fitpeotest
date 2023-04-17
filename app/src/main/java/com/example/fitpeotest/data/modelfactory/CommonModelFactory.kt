package com.example.fitpeotest.data.modelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitpeotest.viewmodel.CommonViewModel
import com.example.fitpeotest.data.ApiHelper
import com.example.fitpeotest.data.repository.MainRepository

class CommonModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CommonViewModel(MainRepository(apiHelper)) as T
}