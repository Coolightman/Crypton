package com.coolightman.crypton.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.coolightman.crypton.domain.entity.CoinInfo

class CoinInfoDiffCallback : DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}