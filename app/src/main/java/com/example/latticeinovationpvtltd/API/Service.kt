package com.example.latticeinovationpvtltd.API

import com.example.latticeinovationpvtltd.DATA.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("top-headlines?country=in&apiKey=2723fe6d30b6429bb61446b7c0571a0e")
    suspend fun getArticleFromAPI(): ResponseModel

//    @GET("everything")
//    suspend fun getSearchedDataFromAPI(
//        @Query("query") query: String,
//        @Query("language") language: String,
//        @Query("sortBy") sortBy: String,
//        @Query("apiKey") apiKey: String
//    ): ResponseModel

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = "2723fe6d30b6429bb61446b7c0571a0e"
    ): Response<ResponseModel>
}