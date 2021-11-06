package com.coolightman.crypton.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.coolightman.crypton.model.data.CoinPriceInfo
import com.coolightman.crypton.model.repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mainRepository = MainRepository.getInstance(application)

    fun getCoinPriceInfoList(): LiveData<List<CoinPriceInfo>> {
        return mainRepository.getCoinPriceInfoList()
    }

    fun getCoinPriceInfo(coinName: String): LiveData<CoinPriceInfo> {
        return mainRepository.getCoinPriceInfo(coinName)
    }
}