package com.srgpanov.ip_bagamanshin.di.remote

import com.srgpanov.ip_bagamanshin.BuildConfig
import com.srgpanov.ip_bagamanshin.model.remote.ApiService
import com.srgpanov.simpleweather.data.remote.ResponseResultAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    companion object{
        private const val BASE_URL = "http://platinum-kaz.ru/"
    }

    @Singleton
    @Provides
    fun getInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
        }
        return loggingInterceptor;
    }

    @Singleton
    @Provides
    fun getHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun createWeatherService(httpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResponseResultAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }



}