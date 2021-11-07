package com.coolightman.crypton.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.coolightman.crypton.R

object FormatValue {

    fun formatPct(context: Context, double: Double): Pair<String, Int> {
        val pct: String
        val color = when {
            double > 0 -> {
                pct = "+${roundPct(double)}%"
                ContextCompat.getColor(context, R.color.pct_up_color)
            }
            double < 0 -> {
                pct = "${roundPct(double)}%"
                ContextCompat.getColor(context, R.color.pct_down_color)
            }
            else -> {
                pct = "${roundPct(double)}%"
                ContextCompat.getColor(context, R.color.pct_zero_color)
            }
        }
        return Pair(pct, color)
    }

    fun formatValueChange(context: Context, double: Double): Pair<String, Int> {
        val value: String
        val color = when {
            double > 0 -> {
                value = "+${roundValue(double)}"
                ContextCompat.getColor(context, R.color.pct_up_color)
            }
            double < 0 -> {
                value = roundValue(double)
                ContextCompat.getColor(context, R.color.pct_down_color)
            }
            else -> {
                value = roundValue(double)
                ContextCompat.getColor(context, R.color.pct_zero_color)
            }
        }
        return Pair(value, color)
    }

    private fun roundPct(double: Double): String {
        return String.format("%.2f".format(double))
    }

    fun roundValue(double: Double): String {
        if (double in -0.000001..0.000001) return "too less"

        val strings = double.toString().split(".")
        val cuted = if (strings[1].length > 5) {
            String.format("%.6f".format(double))
        } else {
            double.toString()
        }

        return cuted.replace("0*$".toRegex(), "")
    }
}