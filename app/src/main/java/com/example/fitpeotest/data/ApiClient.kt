package com.example.fitpeotest.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"  //Dev
    private var retrofit: Retrofit? = null
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }


    private fun getRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        //Timeout
        httpClient.readTimeout(ApiConfig.READ_TIMEOUT, TimeUnit.SECONDS)
        httpClient.connectTimeout(ApiConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(ApiConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)


        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }





    val apiService: ApiInterface = getRetrofit().create(ApiInterface::class.java)


}