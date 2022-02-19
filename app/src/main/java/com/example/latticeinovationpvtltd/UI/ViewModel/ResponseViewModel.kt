package com.example.latticeinovationpvtltd.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.latticeinovationpvtltd.API.NetworkHelperClass
import com.example.latticeinovationpvtltd.API.Resource
import com.example.latticeinovationpvtltd.DATA.Repo.ResponseRepository
import com.example.latticeinovationpvtltd.DATA.models.Article
import com.example.latticeinovationpvtltd.DATA.models.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResponseViewModel: ViewModel() {
    private val responseRepository = ResponseRepository()
    private val mutableLiveData = MutableLiveData<NetworkHelperClass>()
    val liveData: LiveData<NetworkHelperClass> = mutableLiveData
    fun callAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = responseRepository.getDataFromService()
            mutableLiveData.postValue(NetworkHelperClass.OnSuccess_1(response))
        }
    }

    private val mutableLiveDataForSearch = MutableLiveData<NetworkHelperClass>()
    val liveDataForSearch: LiveData<NetworkHelperClass> = mutableLiveDataForSearch

    fun getData(query: String) : LiveData<ResponseModel>{
        CoroutineScope(Dispatchers.IO).launch {
            val repo = responseRepository.getSearchDataFromApi(query)
            mutableLiveData.postValue(NetworkHelperClass.OnSuccess_2(repo))
        }
        return liveData(Dispatchers.IO) {
            val data = responseRepository.getSearchDataFromApi(query)
            emit(data)
        }
    }
}