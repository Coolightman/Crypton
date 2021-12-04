package com.coolightman.crypton.data.network.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coolightman.crypton.data.network.ApiClient.LOGO_URL_ROOT
import com.coolightman.crypton.utils.TimeConverter
import com.google.gson.annotations.SerializedName

@Entity
data class CoinInfoDto(
    @PrimaryKey
    @SerializedName("FROMSYMBOL") var fromSymbol: String,
    @SerializedName("TOSYMBOL") var toSymbol: String,
    @SerializedName("PRICE") var price: Double?,
    @SerializedName("LASTUPDATE") var lastUpdate: Int?,
    @SerializedName("CHANGEHOUR") var changeHour: Double?,
    @SerializedName("CHANGEPCTHOUR") var changePctHour: Double?,
    @SerializedName("OPENHOUR") var openHour: Double?,
    @SerializedName("HIGHHOUR") var highHour: Double?,
    @SerializedName("LOWHOUR") var lowHour: Double?,
    @SerializedName("CHANGEDAY") var changeDay: Double?,
    @SerializedName("CHANGEPCTDAY") var changePctDay: Double?,
    @SerializedName("OPENDAY") var openDay: Double?,
    @SerializedName("HIGHDAY") var highDay: Double?,
    @SerializedName("LOWDAY") var lowDay: Double?,
    @SerializedName("CHANGE24HOUR") var change24Hour: Double?,
    @SerializedName("CHANGEPCT24HOUR") var changePct24Hour: Double?,
    @SerializedName("OPEN24HOUR") var open24Hours: Double?,
    @SerializedName("HIGH24HOUR") var high24Hours: Double?,
    @SerializedName("LOW24HOUR") var low24Hours: Double?,
    @SerializedName("VOLUMEHOUR") var volumeHour: Double?,
    @SerializedName("VOLUMEHOURTO") var volumeHourTo: Double?,
    @SerializedName("VOLUMEDAY") var volumeDay: Double?,
    @SerializedName("VOLUMEDAYTO") var volumeDayTo: Double?,
    @SerializedName("VOLUME24HOUR") var volume24Hours: Double?,
    @SerializedName("VOLUME24HOURTO") var volume24HoursTo: Double?,
    @SerializedName("LASTVOLUME") var lastVolume: Double?,
    @SerializedName("LASTVOLUMETO") var lastVolumeTo: Double?,
    @SerializedName("LASTMARKET") var lastMarket: String?,
    @SerializedName("IMAGEURL") var imageUrl: String?,
) {

    fun getFormattedTime(): String {
        return TimeConverter.convertTimestampToTime(lastUpdate)
    }

    fun getImageFullUrl(): String {
        return LOGO_URL_ROOT + imageUrl
    }
}