package com.coolightman.crypton.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.coolightman.crypton.data.database.model.CoinInfoDbModel

@Dao
interface CoinInfoDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(list: List<CoinInfoDbModel>)

    @Query("select * from coininfodbmodel")
    fun getCoinInfoList(): LiveData<List<CoinInfoDbModel>>

    @Query("select * from coininfodbmodel where fromSymbol = :coinName")
    fun getCoinInfo(coinName: String): LiveData<CoinInfoDbModel>
}