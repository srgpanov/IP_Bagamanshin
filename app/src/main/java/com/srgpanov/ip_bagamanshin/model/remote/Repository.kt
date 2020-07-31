package com.srgpanov.ip_bagamanshin.model.remote

import com.srgpanov.ip_bagamanshin.model.data.Response
import com.srgpanov.simpleweather.data.remote.ResponseResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val service:ApiService) {
    suspend fun fetchData():ResponseResult<Response> = service.getData()
}