package com.coolightman.crypton.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.coolightman.crypton.databinding.ActivityMainBinding
import com.coolightman.crypton.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        createObservers()
        mainViewModel.loadData()
    }

    private fun createObservers() {
        observeCoinPriceInfoList()
    }

    private fun observeCoinPriceInfoList() {
        mainViewModel.getCoinPriceInfoList().observe(this) {
            it?.let {
                if (it.isNotEmpty()) {
                    mainBinding.textViewHello.text = it.toString()
                }
            }
        }
    }
}