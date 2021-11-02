package com.coolightman.crypton.model.data

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinPriceRawData(
    @SerializedName("RAW")
    var coinPriceRawDataJson: JsonObject? = null
)
