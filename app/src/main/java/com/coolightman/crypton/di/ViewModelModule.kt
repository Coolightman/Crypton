package com.coolightman.crypton.di

import androidx.lifecycle.ViewModel
import com.coolightman.crypton.presentation.viewmodel.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinViewModel(viewModel: CoinViewModel): ViewModel
}