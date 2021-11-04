package com.coolightman.crypton.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object TimeConverter {

    fun convertTimestampToTime(lastUpdate: Int?): String{
        lastUpdate?.let {
            val timestamp = Timestamp(lastUpdate * 1000L)
            val date = Date(timestamp.time)
            val pattern = "HH:mm:ss"
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(date)
        }
        return ""
    }
}