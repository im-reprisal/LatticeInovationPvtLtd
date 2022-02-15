package com.example.latticeinovationpvtltd.API

import com.example.latticeinovationpvtltd.DATA.models.Article

sealed class NetworkHelperClass{
    data class OnSuccess(val responseList: List<Article>) : NetworkHelperClass()
}
