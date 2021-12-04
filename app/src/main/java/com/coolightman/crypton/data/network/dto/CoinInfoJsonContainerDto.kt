package com.coolightman.crypton.data.network.dto

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonContainerDto(
    @SerializedName("RAW")
    var json: JsonObject? = null
)
