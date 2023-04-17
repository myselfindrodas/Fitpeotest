package com.example.fitpeotest.data

import com.example.fitpeotest.data.model.PhotoResponseModel
import retrofit2.http.*

interface ApiInterface {


    @GET("photos")
    suspend fun photolist(
    ): PhotoResponseModel


}