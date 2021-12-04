package com.coolightman.crypton.data.models

import com.google.gson.annotations.SerializedName

data class CoinInfoList(
    @SerializedName("Data") var data: List<Data>
)
