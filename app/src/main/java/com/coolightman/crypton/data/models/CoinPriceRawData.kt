package com.coolightman.crypton.data.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinPriceRawData(
    @SerializedName("RAW")
    var coinPriceRawDataJson: JsonObject? = null
)
