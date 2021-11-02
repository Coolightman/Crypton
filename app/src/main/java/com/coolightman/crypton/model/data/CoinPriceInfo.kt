package com.coolightman.crypton.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CoinPriceInfo (
    @SerializedName("TYPE") var TYPE : String,
    @SerializedName("MARKET") var MARKET : String,
    @PrimaryKey
    @SerializedName("FROMSYMBOL") var FROMSYMBOL : String,
    @SerializedName("TOSYMBOL") var TOSYMBOL : String,
    @SerializedName("FLAGS") var FLAGS : String,
    @SerializedName("PRICE") var PRICE : Double,
    @SerializedName("LASTUPDATE") var LASTUPDATE : Int,
    @SerializedName("MEDIAN") var MEDIAN : Int,
    @SerializedName("LASTVOLUME") var LASTVOLUME : Double,
    @SerializedName("LASTVOLUMETO") var LASTVOLUMETO : Double,
    @SerializedName("LASTTRADEID") var LASTTRADEID : String,
    @SerializedName("VOLUMEDAY") var VOLUMEDAY : Double,
    @SerializedName("VOLUMEDAYTO") var VOLUMEDAYTO : Double,
    @SerializedName("VOLUME24HOUR") var VOLUME24HOUR : Double,
    @SerializedName("VOLUME24HOURTO") var VOLUME24HOURTO : Double,
    @SerializedName("OPENDAY") var OPENDAY : Double,
    @SerializedName("HIGHDAY") var HIGHDAY : Double,
    @SerializedName("LOWDAY") var LOWDAY : Double,
    @SerializedName("OPEN24HOUR") var OPEN24HOUR : Double,
    @SerializedName("HIGH24HOUR") var HIGH24HOUR : Double,
    @SerializedName("LOW24HOUR") var LOW24HOUR : Double,
    @SerializedName("LASTMARKET") var LASTMARKET : String,
    @SerializedName("VOLUMEHOUR") var VOLUMEHOUR : Double,
    @SerializedName("VOLUMEHOURTO") var VOLUMEHOURTO : Double,
    @SerializedName("OPENHOUR") var OPENHOUR : Double,
    @SerializedName("HIGHHOUR") var HIGHHOUR : Double,
    @SerializedName("LOWHOUR") var LOWHOUR : Double,
    @SerializedName("TOPTIERVOLUME24HOUR") var TOPTIERVOLUME24HOUR : Double,
    @SerializedName("TOPTIERVOLUME24HOURTO") var TOPTIERVOLUME24HOURTO : Double,
    @SerializedName("CHANGE24HOUR") var CHANGE24HOUR : Double,
    @SerializedName("CHANGEPCT24HOUR") var CHANGEPCT24HOUR : Double,
    @SerializedName("CHANGEDAY") var CHANGEDAY : Double,
    @SerializedName("CHANGEPCTDAY") var CHANGEPCTDAY : Double,
    @SerializedName("CHANGEHOUR") var CHANGEHOUR : Double,
    @SerializedName("CHANGEPCTHOUR") var CHANGEPCTHOUR : Double,
    @SerializedName("CONVERSIONTYPE") var CONVERSIONTYPE : String,
    @SerializedName("CONVERSIONSYMBOL") var CONVERSIONSYMBOL : String,
    @SerializedName("SUPPLY") var SUPPLY : Int,
    @SerializedName("MKTCAP") var MKTCAP : Double,
    @SerializedName("MKTCAPPENALTY") var MKTCAPPENALTY : Int,
    @SerializedName("CIRCULATINGSUPPLY") var CIRCULATINGSUPPLY : Int,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP") var CIRCULATINGSUPPLYMKTCAP : Double,
    @SerializedName("TOTALVOLUME24H") var TOTALVOLUME24H : Double,
    @SerializedName("TOTALVOLUME24HTO") var TOTALVOLUME24HTO : Double,
    @SerializedName("TOTALTOPTIERVOLUME24H") var TOTALTOPTIERVOLUME24H : Double,
    @SerializedName("TOTALTOPTIERVOLUME24HTO") var TOTALTOPTIERVOLUME24HTO : Double,
    @SerializedName("IMAGEURL") var IMAGEURL : String
)