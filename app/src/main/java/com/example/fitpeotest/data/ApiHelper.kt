package com.example.fitpeotest.data


class ApiHelper(private val apiInterface: ApiInterface) {

    suspend fun photolist() = apiInterface.photolist()

}