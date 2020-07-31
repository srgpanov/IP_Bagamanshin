package com.srgpanov.ip_bagamanshin.model.data


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("asdf")
    val asdf: String,
    @SerializedName("qqqq")
    val qqqq: String,
    @SerializedName("url")
    val url: String?=null
)