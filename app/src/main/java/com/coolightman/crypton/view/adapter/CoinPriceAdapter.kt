package com.coolightman.crypton.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coolightman.crypton.R
import com.coolightman.crypton.databinding.CoinPriceItemBinding
import com.coolightman.crypton.model.data.CoinPriceInfo

class CoinPriceAdapter(
    private val context: Context,
    private val listener: (CoinPriceInfo) -> Unit,
) :
    RecyclerView.Adapter<CoinPriceAdapter.CoinPriceViewHolder>() {

    private var coins = listOf<CoinPriceInfo>()

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
        val currentPrice = coin.price.toString()
        val lastUpdate = "${context.resources.getString(R.string.last_update)} ${coin.getFormattedTime()}"

        with(holder) {
            binding.textViewCoinPriceTitle.text = titleText
            binding.textViewPriceNow.text = currentPrice
            binding.textViewLastUpdate.text = lastUpdate

            Glide.with(this.itemView.context)
                .load(coin.getImageFullUrl())
                .into(binding.imageViewCoinLogo)

            itemView.setOnClickListener { listener(coin) }
        }
    }

    override fun getItemCount() = coins.size

    @SuppressLint("NotifyDataSetChanged")
    fun setPrices(coins: List<CoinPriceInfo>) {
        this.coins = coins
        notifyDataSetChanged()
    }
}