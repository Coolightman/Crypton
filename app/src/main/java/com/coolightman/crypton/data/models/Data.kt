package com.coolightman.crypton.data.models

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CoinInfo") var coinInfo : CoinInfo
)
