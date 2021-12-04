package com.coolightman.crypton.domain.entity

import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("Name") var name: String
)
