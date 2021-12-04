package com.coolightman.crypton.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.coolightman.crypton.domain.entity.CoinPriceInfo
import com.coolightman.crypton.data.repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mainRepository = MainRepository.getInstance(application)

    fun getCoinPriceInfoList(): LiveData<List<CoinPriceInfo>> {
        return mainRepository.getCoinPriceInfoList()
    }

    fun getCoinPriceInfo(coinName: String): LiveData<CoinPriceInfo> {
        return mainRepository.getCoinPriceInfo(coinName)
    }
}