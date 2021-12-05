package com.coolightman.crypton.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coolightman.crypton.R
import com.coolightman.crypton.databinding.CoinPriceItemBinding
import com.coolightman.crypton.domain.entity.CoinInfo
import com.coolightman.crypton.utils.FormatValue

class CoinInfoAdapter(
    private val context: Context,
    private val listener: (CoinInfo) -> Unit,
) : RecyclerView.Adapter<CoinInfoViewHolder>() {

    var coins = listOf<CoinInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = CoinPriceItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coins[position]
        val titleText = "${coin.fromSymbol} / ${coin.toSymbol}"
        val formattedTime = coin.lastUpdate
        val lastUpdate =
            "${context.resources.getString(R.string.last_update)} $formattedTime"

        with(holder.binding) {
            textViewCoinPriceTitle.text = titleText
            setPrice(holder, coin.price)
            textViewLastUpdate.text = lastUpdate
            setPctDay(holder, coin.changePctDay)

            Glide.with(root.context)
                .load(coin.imageUrl)
                .into(imageViewCoinLogo)

            root.setOnClickListener { listener(coin) }
        }
    }

    private fun setPrice(holder: CoinInfoViewHolder, price: Double?) {
        price?.let {
            val currentPrice = FormatValue.roundValue(price)
            holder.binding.textViewPriceNow.text = currentPrice
        }
    }

    private fun setPctDay(holder: CoinInfoViewHolder, changePctDay: Double?) {
        changePctDay?.let {
            val pair = FormatValue.formatPct(context, it)
            val pctForDay = pair.first
            val color = pair.second
            holder.binding.textViewChangePctDay.text = pctForDay
            holder.binding.textViewChangePctDay.setTextColor(color)
        }
    }

    override fun getItemCount() = coins.size
}