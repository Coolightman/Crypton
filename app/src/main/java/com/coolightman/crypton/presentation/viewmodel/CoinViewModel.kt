package com.coolightman.crypton.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.coolightman.crypton.data.repository.CoinRepositoryImpl
import com.coolightman.crypton.domain.usecase.GetCoinInfoListUseCase
import com.coolightman.crypton.domain.usecase.GetCoinInfoUseCase
import com.coolightman.crypton.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getCoinInfo(coinName: String) = getCoinInfoUseCase(coinName)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}