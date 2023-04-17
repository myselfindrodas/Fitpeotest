package com.example.fitpeotest.data.repository

import com.example.fitpeotest.data.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun photolist() = apiHelper.photolist()

}