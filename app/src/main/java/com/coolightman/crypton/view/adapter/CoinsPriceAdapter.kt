package com.coolightman.crypton.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coolightman.crypton.R
import com.coolightman.crypton.model.data.CoinPriceInfo

class CoinsPriceAdapter(val listener: (CoinPriceInfo) -> Unit) :
    RecyclerView.Adapter<CoinsPriceAdapter.CoinsPriceViewHolder>() {

    private var prices = listOf<CoinPriceInfo>()

    companion object{
        private const val LOGO_URL_ROOT = "https://www.cryptocompare.com"
    }

    class CoinsPriceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewCoinPriceTitle)
        val priceNow: TextView = itemView.findViewById(R.id.textViewPriceNow)
        val logo: ImageView = itemView.findViewById(R.id.imageViewCoinLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsPriceViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.coin_price_item, parent, false)
        return CoinsPriceViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinsPriceViewHolder, position: Int) {
        val price = prices[position]
        val titleText = "${price.FROMSYMBOL}/${price.TOSYMBOL}"
        holder.title.text = titleText
        holder.priceNow.text = price.PRICE.toString()
        setLogo(holder, price)

        holder.itemView.setOnClickListener { listener(price) }
    }

    private fun setLogo(holder: CoinsPriceViewHolder, price: CoinPriceInfo) {
        Glide.with(holder.itemView.context)
            .load(LOGO_URL_ROOT+price.IMAGEURL)
            .into(holder.logo)
    }

    override fun getItemCount(): Int {
        return prices.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPrices(prices: List<CoinPriceInfo>) {
        this.prices = prices
        notifyDataSetChanged()
    }
}