package com.coolightman.crypton.model.data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CoinInfo") var coinInfo : CoinInfo
)
