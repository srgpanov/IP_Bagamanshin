package com.srgpanov.ip_bagamanshin.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgpanov.ip_bagamanshin.App
import com.srgpanov.ip_bagamanshin.model.data.Response
import com.srgpanov.ip_bagamanshin.model.remote.Repository
import com.srgpanov.ip_bagamanshin.other.SingleLiveEvent
import com.srgpanov.ip_bagamanshin.other.isInternetConnected
import com.srgpanov.simpleweather.data.remote.ResponseResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _data = MutableLiveData<Response>()
    val data: LiveData<Response>
        get() = _data
    val showConnectionDialogEvent = SingleLiveEvent<Unit>()


    init {
        if (App.instance.isInternetConnected){
            fetchData()
        }else{
           showConnectionDialogEvent.value =Unit
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            val response: ResponseResult<Response> = repository.fetchData()
            when (response) {
                is ResponseResult.Success -> _data.postValue(response.data)
                is ResponseResult.Failure->showConnectionDialogEvent.value =Unit
            }
        }
    }
}
