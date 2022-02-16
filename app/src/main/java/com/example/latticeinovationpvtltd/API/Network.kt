package com.example.latticeinovationpvtltd.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val httpLoggingInterceptor= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
        .build()
}