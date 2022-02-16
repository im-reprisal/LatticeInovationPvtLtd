package com.example.latticeinovationpvtltd.API

import com.example.latticeinovationpvtltd.DATA.models.Article
import com.example.latticeinovationpvtltd.DATA.models.ResponseModel

sealed class NetworkHelperClass{
    data class OnSuccess_1(val responseList: List<Article>) : NetworkHelperClass()
    data class OnSuccess_2(val responseList: ResponseModel) : NetworkHelperClass()
    data class OnFailure(val error: String) : NetworkHelperClass()

}
