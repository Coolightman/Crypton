package com.coolightman.crypton.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.coolightman.crypton.data.database.CryptoDatabase
import com.coolightman.crypton.data.mapper.CoinMapper
import com.coolightman.crypton.data.network.ApiClient
import com.coolightman.crypton.domain.entity.CoinInfo
import com.coolightman.crypton.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(application: Application) : CoinRepository {

    private val coinInfoDao = CryptoDatabase.getDb(application).coinInfoDao()
    private val apiService = ApiClient.getService()
    private val mapper = CoinMapper()

    companion object {
        private const val NUMBER_OF_COINS = 30
        private const val LOAD_DELAY = 10 * 1000L
        private const val CURRENCY = "USD"
    }

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getCoinInfoList()) {
            it.map { dbModel -> mapper.mapDbModelToEntity(dbModel) }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getCoinInfo(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            val topCoins = apiService.loadTopCoinNamesList(NUMBER_OF_COINS, CURRENCY)
            val fromSymbols = mapper.mapNamesListToString(topCoins)
            val jsonContainer = apiService.loadCoinInfoList(fromSymbols, CURRENCY)
            val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
            val coinInfoDbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
            coinInfoDao.insert(coinInfoDbModelList)
            delay(LOAD_DELAY)
        }
    }
}