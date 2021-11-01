package com.coolightman.crypton.model.data

import com.google.gson.annotations.SerializedName

data class CoinInfoListDataResponse(
    @SerializedName("Data") var data: List<Data>
)
