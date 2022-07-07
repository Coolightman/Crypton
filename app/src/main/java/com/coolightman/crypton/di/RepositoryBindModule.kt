package com.coolightman.crypton.di

import com.coolightman.crypton.data.repository.CoinRepositoryImpl
import com.coolightman.crypton.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryBindModule {

    @ApplicationScope
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository
}