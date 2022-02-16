package com.example.latticeinovationpvtltd.API

import com.example.latticeinovationpvtltd.DATA.models.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("top-headlines?country=in&apiKey=2723fe6d30b6429bb61446b7c0571a0e")
    suspend fun getArticleFromAPI(): ResponseModel

    @GET("everything")
    suspend fun getSearchedData(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): ResponseModel
}