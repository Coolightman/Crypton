package com.coolightman.crypton.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinInfoDbModel(
    @PrimaryKey
    var fromSymbol: String,
    var toSymbol: String,
    var price: Double?,
    var lastUpdate: Int?,
    var changeHour: Double?,
    var changePctHour: Double?,
    var openHour: Double?,
    var highHour: Double?,
    var lowHour: Double?,
    var changeDay: Double?,
    var changePctDay: Double?,
    var openDay: Double?,
    var highDay: Double?,
    var lowDay: Double?,
    var change24Hour: Double?,
    var changePct24Hour: Double?,
    var open24Hours: Double?,
    var high24Hours: Double?,
    var low24Hours: Double?,
    var volumeHour: Double?,
    var volumeHourTo: Double?,
    var volumeDay: Double?,
    var volumeDayTo: Double?,
    var volume24Hours: Double?,
    var volume24HoursTo: Double?,
    var lastVolume: Double?,
    var lastVolumeTo: Double?,
    var lastMarket: String?,
    var imageUrl: String?,
)