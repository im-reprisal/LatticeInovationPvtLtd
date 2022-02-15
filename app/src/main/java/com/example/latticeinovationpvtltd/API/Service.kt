package com.example.latticeinovationpvtltd.API

import com.example.latticeinovationpvtltd.DATA.models.ResponseModel
import retrofit2.http.GET

interface Service {
    @GET("v2/top-headlines?country=in&apiKey=2723fe6d30b6429bb61446b7c0571a0e")
    suspend fun getArticle(): ResponseModel
}