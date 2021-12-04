package com.coolightman.crypton.domain.repository

import androidx.lifecycle.LiveData
import com.coolightman.crypton.domain.entities.CoinInfo

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>
}