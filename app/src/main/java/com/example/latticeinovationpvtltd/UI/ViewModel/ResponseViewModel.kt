package com.example.latticeinovationpvtltd.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latticeinovationpvtltd.API.NetworkHelperClass
import com.example.latticeinovationpvtltd.DATA.Repo.ResponseRepository
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
            mutableLiveData.postValue(NetworkHelperClass.OnSuccess(response))
        }
    }
}