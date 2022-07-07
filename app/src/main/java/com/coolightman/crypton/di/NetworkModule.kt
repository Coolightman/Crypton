package com.coolightman.crypton.di

import com.coolightman.crypton.data.network.ApiClient
import com.coolightman.crypton.data.network.ApiService
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun provideApi(): ApiService {
        return ApiClient.getApiService()
    }
}