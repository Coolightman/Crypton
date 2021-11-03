package com.coolightman.crypton.model.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.coolightman.crypton.model.data.CoinPriceInfo
import com.coolightman.crypton.model.db.CryptoDatabase
import com.coolightman.crypton.model.network.ApiFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.*

class MainRepository(application: Application) {

    private val apiService = ApiFactory.getService()
    private val database = CryptoDatabase.getDb(application)

    private val coroutineContextIO = Job() + Dispatchers.IO
    private val coroutineContextMain = Job() + Dispatchers.Main
    private val scopeUI = CoroutineScope(coroutineContextIO)
    private val scopeMain = CoroutineScope(coroutineContextMain)
    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("CoroutineException", "throw $throwable")
    }

    companion object {
        private const val LIMIT = 20
        private const val CURRENCY = "USD"
        private const val DELAY_TIME = 10 * 1000L
        private const val REPEAT_NUMBER = 1000
    }

    init {
        loadData()
    }

    fun getCoinPriceInfoList(): LiveData<List<CoinPriceInfo>> {
        return database.coinPriceInfoDao().getCoinPriceInfoList()
    }

    fun getCoinPriceInfo(coinName: String): LiveData<CoinPriceInfo> {
        return database.coinPriceInfoDao().getCoinPriceInfo(coinName)
    }

    private fun loadData() {
        scopeUI.launch(handler) {
            repeat(REPEAT_NUMBER) {
                try {
                    doLoad()
                } catch (e: Exception) {
                    Log.e("CoroutineRepeat", "throw $e")
                }
                delay(DELAY_TIME)
            }
        }
    }

    private suspend fun doLoad() {
        val response = apiService.loadCoinInfoListData(LIMIT, CURRENCY)
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                if (it.data.isNotEmpty()) {
                    val list = it.data.map { data -> data.coinInfo.name }
                    loadPriceInfo(list)
                }
            }
        }
    }

    private suspend fun loadPriceInfo(list: List<String>) {
        val coinsString = list.joinToString(",")
        Log.e("coinsString", coinsString)
        val response = apiService.loadFullPriceList(coinsString, CURRENCY)
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                val json = it.coinPriceRawDataJson
                json?.let { js -> parseCoinsPriceInfo(js) }
            }
        }
    }

    private suspend fun parseCoinsPriceInfo(jsonObject: JsonObject) {
        val priceList = mutableListOf<CoinPriceInfo>()
        val coinKeySet = jsonObject.keySet()
        for (coin in coinKeySet) {
            val coinPriceInfoJson = jsonObject.getAsJsonObject(coin)
            val currencyKeySet = coinPriceInfoJson.keySet()
            for (currency in currencyKeySet) {
                val priceInfoJson = coinPriceInfoJson.getAsJsonObject(currency)
                val coinPriceInfo = Gson().fromJson(priceInfoJson, CoinPriceInfo::class.java)
                priceList.add(coinPriceInfo)
            }
        }
        database.coinPriceInfoDao().insert(priceList)
    }
}