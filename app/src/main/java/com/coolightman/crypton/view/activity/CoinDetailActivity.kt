package com.coolightman.crypton.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.coolightman.crypton.R
import com.coolightman.crypton.databinding.ActivityCoinDetailBinding
import com.coolightman.crypton.viewmodel.MainViewModel

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityCoinDetailBinding

    companion object {
        private const val EXTRA_COIN_NAME = "coinName"

        fun newIntent(context: Context, coinName: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_COIN_NAME, coinName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val coinName = intent.getStringExtra(EXTRA_COIN_NAME)

        coinName?.let {
            createObserver(it)
        }

    }

    private fun createObserver(coinName: String) {
        mainViewModel.getCoinPriceInfo(coinName).observe(this) {
            it?.let {
                Log.e("coinInfo", it.toString())
                setCoinLogo(it.getImageFullUrl())
                binding.textViewFromSymbol.text = it.fromSymbol
                binding.textViewToSymbol.text = it.toSymbol
            }
        }
    }

    private fun setCoinLogo(imageFullUrl: String) {
        Glide.with(this)
            .load(imageFullUrl)
            .into(binding.imageViewCoinLogo)
    }
}