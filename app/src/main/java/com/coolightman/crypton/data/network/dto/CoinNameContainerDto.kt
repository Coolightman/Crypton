package com.coolightman.crypton.data.network.dto

import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto(
    @SerializedName("CoinInfo") var coinName : CoinNameDto
)
