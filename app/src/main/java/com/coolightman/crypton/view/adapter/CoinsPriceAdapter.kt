package com.coolightman.crypton.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coolightman.crypton.databinding.CoinPriceItemBinding
import com.coolightman.crypton.model.data.CoinPriceInfo

class CoinsPriceAdapter(val listener: (CoinPriceInfo) -> Unit) :
    RecyclerView.Adapter<CoinsPriceAdapter.CoinsPriceViewHolder>() {

    private var prices = listOf<CoinPriceInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsPriceViewHolder {
        val binding = CoinPriceItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinsPriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsPriceViewHolder, position: Int) {
        val price = prices[position]
        val titleText = "${price.fromSymbol} / ${price.toSymbol}"
        val lastUpdate = "Last update: ${price.getFormattedTime()}"

        with(holder) {
            binding.textViewCoinPriceTitle.text = titleText
            binding.textViewPriceNow.text = price.price.toString()
            binding.textViewLastUpdate.text = lastUpdate

            Glide.with(this.itemView.context)
                .load(price.getImageFullUrl())
                .into(binding.imageViewCoinLogo)

            holder.itemView.setOnClickListener { listener(price) }
        }
    }

    override fun getItemCount() = prices.size

    inner class CoinsPriceViewHolder(val binding: CoinPriceItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setPrices(prices: List<CoinPriceInfo>) {
        this.prices = prices
        notifyDataSetChanged()
    }
}