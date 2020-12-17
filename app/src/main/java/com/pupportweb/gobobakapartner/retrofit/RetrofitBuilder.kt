package com.pupportweb.gobobakapartner.retrofit

import com.omelette.stockjar.Retrofit.ApiEndpointInterface
import com.pupportweb.gobobakapartner.Config.BaseURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitBuilder private constructor()
{

    var BASE_URL = BaseURL.BASE_URL;


    val retrofit: ApiEndpointInterface
        get() {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(10, TimeUnit.MINUTES)
            httpClient.readTimeout(10, TimeUnit.MINUTES)
            httpClient.writeTimeout(10, TimeUnit.MINUTES)
            httpClient.addInterceptor(logging)
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
            return retrofit.create(ApiEndpointInterface::class.java)
        }

    companion object {

        val instance = RetrofitBuilder()
    }

}