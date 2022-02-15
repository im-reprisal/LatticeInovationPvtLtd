package com.example.latticeinovationpvtltd.DATA.Repo

import com.example.latticeinovationpvtltd.API.Network
import com.example.latticeinovationpvtltd.API.Service
import com.example.latticeinovationpvtltd.DATA.models.Article

class ResponseRepository {
    private fun getService(): Service = Network.getRetrofit().create(Service::class.java)
    suspend fun getDataFromService(): List<Article> {
        return getService().getArticle().articles
    }
}