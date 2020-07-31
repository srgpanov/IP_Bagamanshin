package com.srgpanov.ip_bagamanshin.model.remote

import com.srgpanov.ip_bagamanshin.model.data.Response
import com.srgpanov.simpleweather.data.remote.ResponseResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("cloaka.php")
    suspend fun getData(@Query("id") query: String="2ottk6qvq3n63jec38t8"): ResponseResult<Response>

}
