package com.coolightman.crypton.model.db

import androidx.room.TypeConverter
import com.coolightman.crypton.model.data.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun listDataToJson(movies: List<Data>): String {
        return Gson().toJson(movies)
    }

    @TypeConverter
    fun jsonToListData(json: String): List<Data> {
        val itemType = object : TypeToken<List<Data>>() {}.type
        return Gson().fromJson(json, itemType)
    }
}