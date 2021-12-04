package com.coolightman.crypton.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.coolightman.crypton.data.models.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(list: List<CoinPriceInfo>)

    @Query("select * from coinpriceinfo")
    fun getCoinPriceInfoList(): LiveData<List<CoinPriceInfo>>

    @Query("select * from coinpriceinfo where fromSymbol = :coinName")
    fun getCoinPriceInfo(coinName: String): LiveData<CoinPriceInfo>
}