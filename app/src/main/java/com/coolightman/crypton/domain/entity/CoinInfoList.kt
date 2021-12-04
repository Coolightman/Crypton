package com.coolightman.crypton.domain.entity

import com.google.gson.annotations.SerializedName

data class CoinInfoList(
    @SerializedName("Data") var data: List<Data>
)
