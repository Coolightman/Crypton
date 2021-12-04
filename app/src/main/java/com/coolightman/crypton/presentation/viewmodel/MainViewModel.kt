package com.coolightman.crypton.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.coolightman.crypton.data.network.dto.CoinInfoDto

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mainRepository = MainRepository.getInstance(application)

    fun getCoinPriceInfoList(): LiveData<List<CoinInfoDto>> {
        return mainRepository.getCoinPriceInfoList()
    }

    fun getCoinPriceInfo(coinName: String): LiveData<CoinInfoDto> {
        return mainRepository.getCoinPriceInfo(coinName)
    }
}