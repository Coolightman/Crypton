package com.coolightman.crypton.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coolightman.crypton.R
import com.coolightman.crypton.databinding.CoinPriceItemBinding
import com.coolightman.crypton.data.network.dto.CoinInfoDto
import com.coolightman.crypton.utils.FormatValue

class CoinPriceAdapter(
    private val context: Context,
    private val listener: (CoinInfoDto) -> Unit,
) :
    RecyclerView.Adapter<CoinPriceAdapter.CoinPriceViewHolder>() {

    private var coins = listOf<CoinInfoDto>()

    inner class CoinPriceViewHolder(val binding: CoinPriceItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinPriceViewHolder {
        val binding = CoinPriceItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinPriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinPriceViewHolder, position: Int) {
        val coin = coins[position]
        val titleText = "${coin.fromSymbol} / ${coin.toSymbol}"
        val lastUpdate =
            "${context.resources.getString(R.string.last_update)} ${coin.getFormattedTime()}"

        with(holder) {
            binding.textViewCoinPriceTitle.text = titleText
            setPrice(holder, coin.price)
            binding.textViewLastUpdate.text = lastUpdate
            setPctDay(holder, coin.changePctDay)

            Glide.with(this.itemView.context)
                .load(coin.getImageFullUrl())
                .into(binding.imageViewCoinLogo)

            itemView.setOnClickListener { listener(coin) }
        }
    }

    private fun setPrice(holder: CoinPriceViewHolder, price: Double?) {
        price?.let {
            val currentPrice = FormatValue.roundValue(price)
            holder.binding.textViewPriceNow.text = currentPrice
        }
    }

    private fun setPctDay(holder: CoinPriceViewHolder, changePctDay: Double?) {
        changePctDay?.let {
            val pair = FormatValue.formatPct(context, it)
            val pctForDay = pair.first
            val color = pair.second
            holder.binding.textViewChangePctDay.text = pctForDay
            holder.binding.textViewChangePctDay.setTextColor(color)
        }
    }

    override fun getItemCount() = coins.size

    @SuppressLint("NotifyDataSetChanged")
    fun setPrices(coinDtos: List<CoinInfoDto>) {
        this.coins = coinDtos
        notifyDataSetChanged()
    }
}