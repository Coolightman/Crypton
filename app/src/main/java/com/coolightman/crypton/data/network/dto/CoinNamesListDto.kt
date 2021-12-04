package com.coolightman.crypton.data.network.dto

import com.google.gson.annotations.SerializedName

data class CoinNamesListDto(
    @SerializedName("Data") var names: List<CoinNameContainerDto>
)
