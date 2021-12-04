package com.coolightman.crypton.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.coolightman.crypton.databinding.ActivityMainBinding
import com.coolightman.crypton.domain.entity.CoinPriceInfo
import com.coolightman.crypton.presentation.adapter.CoinPriceAdapter
import com.coolightman.crypton.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var coinPriceAdapter: CoinPriceAdapter

    companion object {
        const val NUMBER_OF_COINS = 25
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        createObservers()
        createAdapter()
    }

    private fun createObservers() {
        observeCoinPriceInfoList()
    }

    private fun observeCoinPriceInfoList() {
        mainViewModel.getCoinPriceInfoList().observe(this) {
            it?.let {
                if (it.isNotEmpty()) {
                    val list = it.takeLast(NUMBER_OF_COINS)
                    coinPriceAdapter.setPrices(list)
                }
            }
        }
    }

    private fun createAdapter() {
        coinPriceAdapter = CoinPriceAdapter(this) { onClickCoin(it) }
        binding.recyclerViewCoinPrice.adapter = coinPriceAdapter
    }

    private fun onClickCoin(coin: CoinPriceInfo) {
        val intent = CoinDetailActivity.newIntent(this, coin.fromSymbol)
        startActivity(intent)
    }
}