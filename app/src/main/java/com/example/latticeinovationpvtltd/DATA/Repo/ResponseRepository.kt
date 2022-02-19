package com.example.latticeinovationpvtltd.DATA.Repo

import com.example.latticeinovationpvtltd.API.Network
import com.example.latticeinovationpvtltd.API.Service
import com.example.latticeinovationpvtltd.DATA.models.Article
import com.example.latticeinovationpvtltd.DATA.models.ResponseModel

class ResponseRepository {
    private fun getService(): Service = Network.getRetrofit().create(Service::class.java)
    suspend fun getDataFromService(): List<Article> {
        return getService().getArticleFromAPI().articles
    }
    suspend fun getSearchDataFromApi(query: String): List<Article> {
        return getService().getSearchedData(query, "2723fe6d30b6429bb61446b7c0571a0e").articles
    }
}