package com.coolightman.crypton.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.coolightman.crypton.model.data.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(list: List<CoinPriceInfo>)

    @Query("select * from coinpriceinfo")
    fun getCoinPriceInfoList(): LiveData<List<CoinPriceInfo>>

    @Query("select * from coinpriceinfo where FROMSYMBOL = :fromSymbol")
    fun getCoinPriceInfo(fromSymbol: String): LiveData<CoinPriceInfo>
}