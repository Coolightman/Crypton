package com.coolightman.crypton.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.coolightman.crypton.data.database.CryptoDatabase
import com.coolightman.crypton.data.database.dao.CoinInfoDao
import com.coolightman.crypton.data.mapper.CoinMapper
import com.coolightman.crypton.data.network.ApiClient
import com.coolightman.crypton.data.network.ApiService
import com.coolightman.crypton.domain.entity.CoinInfo
import com.coolightman.crypton.domain.repository.CoinRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : CoinRepository {

    companion object {
        private const val LOAD_DELAY = 10 * 1000L
        private const val CURRENCY = "USD"
        private const val NUMBER_OF_COINS = 30
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
            try {
                val topCoins = apiService.loadTopCoinNamesList(NUMBER_OF_COINS, CURRENCY)
                val fromSymbols = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.loadCoinInfoList(fromSymbols, CURRENCY)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val coinInfoDbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insert(coinInfoDbModelList)
            } catch (e: Exception) {
            }
            delay(LOAD_DELAY)
        }
    }
}