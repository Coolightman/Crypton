package com.coolightman.crypton.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.coolightman.crypton.R

object FormatValue {

    fun formatPct(context: Context, double: Double): Pair<String, Int> {
        val pctForDay: String
        val color = when {
            double > 0 -> {
                pctForDay = "+${roundPct(double)}%"
                ContextCompat.getColor(context, R.color.pct_up_color)
            }
            double < 0 -> {
                pctForDay = "${roundPct(double)}%"
                ContextCompat.getColor(context, R.color.pct_down_color)
            }
            else -> {
                pctForDay = "${roundPct(double)}%"
                ContextCompat.getColor(context, R.color.pct_zero_color)
            }
        }
        return Pair(pctForDay, color)
    }

    private fun roundPct(double: Double): String {
        return String.format("%.2f".format(double))
    }

    fun roundValue(double: Double): String {
        val strings = double.toString().split(".")
        return if (strings[1].length > 5) {
            String.format("%.6f".format(double))
        } else {
            double.toString()
        }
    }
}