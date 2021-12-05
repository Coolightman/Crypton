package com.coolightman.crypton.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.coolightman.crypton.R
import com.coolightman.crypton.databinding.ActivityMainBinding
import com.coolightman.crypton.domain.entity.CoinInfo
import com.coolightman.crypton.presentation.adapter.CoinInfoAdapter
import com.coolightman.crypton.presentation.viewmodel.CoinViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.activity_main)
    }
}