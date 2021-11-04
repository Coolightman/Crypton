package com.coolightman.crypton.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coolightman.crypton.model.network.ApiFactory.LOGO_URL_ROOT
import com.coolightman.crypton.utils.TimeConverter
import com.google.gson.annotations.SerializedName

@Entity
data class CoinPriceInfo(
    @SerializedName("TYPE") var type: String?,
    @SerializedName("MARKET") var market: String?,
    @PrimaryKey
    @SerializedName("FROMSYMBOL") var fromSymbol: String,
    @SerializedName("TOSYMBOL") var toSymbol: String,
    @SerializedName("FLAGS") var flags: String?,
    @SerializedName("PRICE") var price: Double?,
    @SerializedName("LASTUPDATE") var lastUpdate: Int?,
    @SerializedName("MEDIAN") var median: Int?,
    @SerializedName("LASTVOLUME") var lastVolume: Double?,
    @SerializedName("LASTVOLUMETO") var lastVolumeTo: Double?,
    @SerializedName("LASTTRADEID") var lastTradeId: String?,
    @SerializedName("VOLUMEDAY") var volumeDay: Double?,
    @SerializedName("VOLUMEDAYTO") var volumeDayTo: Double?,
    @SerializedName("VOLUME24HOUR") var volume24Hours: Double?,
    @SerializedName("VOLUME24HOURTO") var volume24HoursTo: Double?,
    @SerializedName("OPENDAY") var openDay: Double?,
    @SerializedName("HIGHDAY") var highDay: Double?,
    @SerializedName("LOWDAY") var lowDay: Double?,
    @SerializedName("OPEN24HOUR") var open24Hours: Double?,
    @SerializedName("HIGH24HOUR") var high24Hours: Double?,
    @SerializedName("LOW24HOUR") var low24Hours: Double?,
    @SerializedName("LASTMARKET") var lastMarket: String?,
    @SerializedName("VOLUMEHOUR") var volumeHour: Double?,
    @SerializedName("VOLUMEHOURTO") var volumeHourTo: Double?,
    @SerializedName("OPENHOUR") var openHour: Double?,
    @SerializedName("HIGHHOUR") var highHour: Double?,
    @SerializedName("LOWHOUR") var lowHour: Double?,
    @SerializedName("TOPTIERVOLUME24HOUR") var topTierVolume24Hour: Double?,
    @SerializedName("TOPTIERVOLUME24HOURTO") var topTierVolume24HourTo: Double?,
    @SerializedName("CHANGE24HOUR") var change24Hour: Double?,
    @SerializedName("CHANGEPCT24HOUR") var changePct24Hour: Double?,
    @SerializedName("CHANGEDAY") var changeDay: Double?,
    @SerializedName("CHANGEPCTDAY") var changePctDay: Double?,
    @SerializedName("CHANGEHOUR") var changeHour: Double?,
    @SerializedName("CHANGEPCTHOUR") var changePctHour: Double?,
    @SerializedName("CONVERSIONTYPE") var conversionType: String?,
    @SerializedName("CONVERSIONSYMBOL") var conversionSymbol: String?,
    @SerializedName("SUPPLY") var supply: Int?,
    @SerializedName("MKTCAP") var mktCap: Double?,
    @SerializedName("MKTCAPPENALTY") var mktCapPenalty: Int?,
    @SerializedName("CIRCULATINGSUPPLY") var circulatingSupply: Int?,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP") var circulatingSupplyMktCap: Double?,
    @SerializedName("TOTALVOLUME24H") var totalVolume24Hour: Double?,
    @SerializedName("TOTALVOLUME24HTO") var totalVolume24HourTo: Double?,
    @SerializedName("TOTALTOPTIERVOLUME24H") var totalTopTierVolume24Hour: Double?,
    @SerializedName("TOTALTOPTIERVOLUME24HTO") var totalTopTierVolume24HourTo: Double?,
    @SerializedName("IMAGEURL") var imageUrl: String?
) {

    fun getFormattedTime(): String {
        return TimeConverter.convertTimestampToTime(lastUpdate)
    }

    fun getImageFullUrl(): String {
        return LOGO_URL_ROOT + imageUrl
    }
}