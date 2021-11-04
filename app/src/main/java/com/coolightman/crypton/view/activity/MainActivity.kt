package com.coolightman.crypton.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coolightman.crypton.databinding.ActivityMainBinding
import com.coolightman.crypton.model.data.CoinPriceInfo
import com.coolightman.crypton.view.adapter.CoinsPriceAdapter
import com.coolightman.crypton.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var coinsPriceAdapter: CoinsPriceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
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
                    coinsPriceAdapter.setPrices(it)
                }
            }
        }
    }

    private fun createAdapter() {
        coinsPriceAdapter = CoinsPriceAdapter(this) { onClickCoin(it) }
        mainBinding.recyclerViewCoinsPrice.adapter = coinsPriceAdapter
    }

    private fun onClickCoin(it: CoinPriceInfo) {
        Toast.makeText(this, "Click ${it.fromSymbol}", Toast.LENGTH_SHORT).show()
    }
}