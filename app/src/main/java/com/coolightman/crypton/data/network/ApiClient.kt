package com.coolightman.crypton.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null
    private const val API_URL = "https://min-api.cryptocompare.com/data/"
    const val LOGO_URL_ROOT = "https://www.cryptocompare.com"
    private val LOCK = Any()

    private fun getClient(): Retrofit {
        synchronized(LOCK) {
            retrofit?.let { return it }
            val instance = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit = instance
            return instance
        }
    }

    fun getService(): ApiService {
        return getClient().create(ApiService::class.java)
    }
}