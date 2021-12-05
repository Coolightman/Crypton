package com.coolightman.crypton.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.coolightman.crypton.databinding.ActivityMainBinding
import com.coolightman.crypton.domain.entity.CoinInfo
import com.coolightman.crypton.presentation.adapter.CoinInfoAdapter
import com.coolightman.crypton.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var coinInfoAdapter: CoinInfoAdapter

    companion object {
        const val NUMBER_OF_COINS = 30
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(binding.root)

        createObservers()
        createAdapter()
    }

    private fun createObservers() {
        observeCoinPriceInfoList()
    }

    private fun observeCoinPriceInfoList() {
        mainViewModel.coinInfoList.observe(this) {
            it?.let {
                if (it.isNotEmpty()) {
                    val coins = it.takeLast(NUMBER_OF_COINS)
                    coinInfoAdapter.submitList(coins)
                }
            }
        }
    }

    private fun createAdapter() {
        coinInfoAdapter = CoinInfoAdapter(this) { onClickCoin(it) }
        binding.recyclerViewCoinPrice.adapter = coinInfoAdapter
    }

    private fun onClickCoin(coinDto: CoinInfo) {
        val intent = CoinDetailActivity.newIntent(this, coinDto.fromSymbol)
        startActivity(intent)
    }
}