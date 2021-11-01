package com.coolightman.crypton.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CoinInfo(
    @PrimaryKey
    @SerializedName("Id") var id : String,
    @SerializedName("Name") var name : String,
    @SerializedName("FullName") var fullName : String,
    @SerializedName("ImageUrl") var imageUrl : String
)
