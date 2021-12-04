package com.coolightman.crypton.domain.entity

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CoinInfo") var coinInfo : CoinInfo
)
