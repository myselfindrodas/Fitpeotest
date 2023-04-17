package com.example.fitpeotest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fitpeotest.data.Resource
import com.example.fitpeotest.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers


class CommonViewModel(private val mainRepository: MainRepository) : ViewModel() {


    fun photolist() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = mainRepository.photolist()
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }




}