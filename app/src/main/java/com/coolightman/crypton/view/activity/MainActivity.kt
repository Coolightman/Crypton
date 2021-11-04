package com.coolightman.crypton.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.coolightman.crypton.databinding.ActivityMainBinding
import com.coolightman.crypton.model.data.CoinPriceInfo
import com.coolightman.crypton.view.adapter.CoinPriceAdapter
import com.coolightman.crypton.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var coinPriceAdapter: CoinPriceAdapter

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
                    coinPriceAdapter.setPrices(it)
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