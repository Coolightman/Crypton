package com.coolightman.crypton.model.data

import com.google.gson.annotations.SerializedName

data class CoinInfoList(
    @SerializedName("Data") var data: List<Data>
)
