package com.coolightman.crypton.model.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.coolightman.crypton.model.data.CoinInfo
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
        private const val LIMIT = 10
        private const val CURRENCY = "USD"
    }

    fun getCoinPriceInfoList(): LiveData<List<CoinPriceInfo>> {
        return database.coinPriceInfoDao().getCoinPriceInfoList()
    }

    fun loadData() {
        scopeUI.launch(handler) {
            val response = apiService.loadCoinInfoListData(LIMIT, CURRENCY)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    if (it.data.isNotEmpty()) {
                        val list = it.data.map { data -> data.coinInfo }
                        loadPriceInfo(list)
                    }
                }
            }
        }
    }

    private suspend fun loadPriceInfo(list: List<CoinInfo>) {
        val coinInfoString = getCoinInfoString(list)
        val response = apiService.loadFullPriceList(coinInfoString, CURRENCY)
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                val json = it.coinPriceRawDataJson
                Log.i("json", json.toString())
                json?.let { js -> parseCoinsPriceInfo(js, list) }
            }
        }
    }

    private suspend fun parseCoinsPriceInfo(jsonObject: JsonObject, list: List<CoinInfo>) {
        val priceList = mutableListOf<CoinPriceInfo>()
        for (coin in list) {
            val coinPriceInfoJson = jsonObject.getAsJsonObject(coin.name).getAsJsonObject(CURRENCY)
            val coinPriceInfo = Gson().fromJson(coinPriceInfoJson, CoinPriceInfo::class.java)
            priceList.add(coinPriceInfo)
        }
        Log.i("priceList", priceList.toString())
        database.coinPriceInfoDao().insert(priceList)
    }

    private fun getCoinInfoString(list: List<CoinInfo>): String {
        val string = StringBuilder()
        for (coin in list) {
            string.append("${coin.name},")
        }
        return removeLastSymbol(string.toString())
    }

    private fun removeLastSymbol(string: String): String {
        return string.substring(0, string.lastIndex)
    }

}