package com.anara.helloworld

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val data = MutableLiveData<List<Covid>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        this.retrieveDataFromServer()
    }

    private fun retrieveDataFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                status.postValue(ApiStatus.LOADING)
                val result = CovidApi.service.getCovid()
                status.postValue(ApiStatus.SUCCESS)
                data.postValue(result.update.harian.sortedByDescending { it.key })
            } catch (e: Exception) {
                status.postValue(ApiStatus.FAILED)
                Log.d("MainViewModel", "Gagal: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Covid>> = this.data

    fun getStatus(): LiveData<ApiStatus> = this.status

}