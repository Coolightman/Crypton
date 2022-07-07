package com.coolightman.crypton.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coolightman.crypton.domain.usecase.GetCoinInfoListUseCase
import com.coolightman.crypton.domain.usecase.GetCoinInfoUseCase
import com.coolightman.crypton.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Coroutine_exception", throwable.stackTraceToString())
    }

    val coinInfoList = getCoinInfoListUseCase()

    fun getCoinInfo(coinName: String) = getCoinInfoUseCase(coinName)

    init {
        viewModelScope.launch(handler) {
            loadDataUseCase()
        }
    }
}