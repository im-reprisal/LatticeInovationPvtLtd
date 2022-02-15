package com.example.latticeinovationpvtltd.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latticeinovationpvtltd.API.NetworkHelperClass
import com.example.latticeinovationpvtltd.DATA.Repo.ResponseRepository
import com.example.latticeinovationpvtltd.DATA.models.ResponseModel
import com.example.latticeinovationpvtltd.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class ResponseViewModel: ViewModel() {
    val responseRepository = ResponseRepository()
    val mutableLiveData = MutableLiveData<NetworkHelperClass>()
    val liveData: LiveData<NetworkHelperClass> = mutableLiveData
    fun callAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = responseRepository.getDataFromService()
            mutableLiveData.postValue(NetworkHelperClass.OnSuccess(response))
        }
    }
    private fun handleSearchNewsResponse(response: Response<ResponseModel>) : Resource<ResponseModel> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}