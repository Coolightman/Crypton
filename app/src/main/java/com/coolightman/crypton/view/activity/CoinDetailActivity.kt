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
import com.coolightman.crypton.utils.FormatValue
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
                setCurrentPrice(it.price)
                setHourChange(it.changeHour)
                setHourChangePct(it.changePctHour)
                setHourOpen(it.openHour)
                setHourHigh(it.highHour)
                setHourLow(it.lowHour)
                setDayChange(it.changeDay)
                setDayChangePct(it.changePctDay)
                setDayOpen(it.openDay)
                setDayHigh(it.highDay)
                setDayLow(it.lowDay)
                set24Change(it.change24Hour)
                set24ChangePct(it.changePct24Hour)
                set24Open(it.open24Hours)
                set24High(it.high24Hours)
                set24Low(it.low24Hours)
                setVolumeFromLabel(it.fromSymbol)
                setVolumeToLabel(it.toSymbol)
                setHourVolume(it.volumeHour)
                setHourVolumeTo(it.volumeHourTo)
                setDayVolume(it.volumeDay)
                setDayVolumeTo(it.volumeDayTo)
                set24Volume(it.volume24Hours)
                set24VolumeTo(it.volume24HoursTo)
                setLastVolume(it.lastVolume)
                setLastVolumeTo(it.lastVolumeTo)
                setLastVolumeMarket(it.lastMarket)
            }
        }
    }

    private fun setLastVolumeMarket(lastMarket: String?) {
        lastMarket?.let {
            binding.textViewVolumeLastMarket.text = it
        }
    }

    private fun setLastVolume(lastVolume: Double?) {
        lastVolume?.let {
            binding.textViewVolumeLast.text = it.toString()
        }
    }

    private fun setLastVolumeTo(lastVolumeTo: Double?) {
        lastVolumeTo?.let {
            binding.textViewVolumeLastTo.text = it.toString()
        }
    }

    private fun setVolumeFromLabel(fromSymbol: String) {
        binding.textViewVolumeFromL.text = fromSymbol
    }

    private fun setVolumeToLabel(toSymbol: String) {
        binding.textViewVolumeToL.text = toSymbol
    }

    private fun setHourVolume(volumeHour: Double?) {
        volumeHour?.let {
            binding.textViewVolumeHour.text = it.toInt().toString()
        }
    }

    private fun setHourVolumeTo(volumeHourTo: Double?) {
        volumeHourTo?.let {
            binding.textViewVolumeHourTo.text = it.toLong().toString()
        }
    }

    private fun setDayVolume(volumeDay: Double?) {
        volumeDay?.let {
            binding.textViewVolumeDay.text = it.toLong().toString()
        }
    }

    private fun setDayVolumeTo(volumeDayTo: Double?) {
        volumeDayTo?.let {
            binding.textViewVolumeDayTo.text = it.toLong().toString()
        }
    }

    private fun set24Volume(volume24Hours: Double?) {
        volume24Hours?.let {
            binding.textViewVolume24.text = it.toLong().toString()
        }
    }

    private fun set24VolumeTo(volume24HoursTo: Double?) {
        volume24HoursTo?.let {
            binding.textViewVolume24To.text = it.toLong().toString()
        }
    }

    private fun set24Change(change24Hour: Double?) {
        change24Hour?.let {
            val pair = FormatValue.formatValueChange(this, it)
            val text = pair.first
            val color = pair.second
            binding.textView24Change.text = text
            binding.textView24Change.setTextColor(color)
        }
    }

    private fun set24ChangePct(changePct24Hour: Double?) {
        changePct24Hour?.let {
            val pair = FormatValue.formatPct(this, it)
            val text = pair.first
            val color = pair.second
            binding.textView24ChangePct.text = text
            binding.textView24ChangePct.setTextColor(color)
        }
    }

    private fun set24Open(open24Hours: Double?) {
        open24Hours?.let {
            binding.textView24Open.text = it.toString()
        }
    }

    private fun set24High(high24Hours: Double?) {
        high24Hours?.let {
            binding.textView24High.text = it.toString()
        }
    }

    private fun set24Low(low24Hours: Double?) {
        low24Hours?.let {
            binding.textView24Low.text = it.toString()
        }
    }

    private fun setDayChange(changeDay: Double?) {
        changeDay?.let {
            val pair = FormatValue.formatValueChange(this, it)
            val text = pair.first
            val color = pair.second
            binding.textViewDayChange.text = text
            binding.textViewDayChange.setTextColor(color)
        }
    }

    private fun setDayChangePct(changePctDay: Double?) {
        changePctDay?.let {
            val pair = FormatValue.formatPct(this, it)
            val text = pair.first
            val color = pair.second
            binding.textViewDayChangePct.text = text
            binding.textViewDayChangePct.setTextColor(color)
        }
    }

    private fun setDayOpen(openDay: Double?) {
        openDay?.let {
            binding.textViewDayOpen.text = it.toString()
        }
    }

    private fun setDayHigh(highDay: Double?) {
        highDay?.let {
            binding.textViewDayHigh.text = it.toString()
        }
    }

    private fun setDayLow(lowDay: Double?) {
        lowDay?.let {
            binding.textViewDayLow.text = it.toString()
        }
    }

    private fun setCurrentPrice(price: Double?) {
        price?.let {
            val currentPrice = FormatValue.roundValue(price)
            binding.textViewCurrentPrice.text = currentPrice
        }
    }

    private fun setHourOpen(openHour: Double?) {
        openHour?.let {
            binding.textViewHourOpen.text = it.toString()
        }
    }

    private fun setHourHigh(highHour: Double?) {
        highHour?.let {
            binding.textViewHourHigh.text = it.toString()
        }
    }

    private fun setHourLow(lowHour: Double?) {
        lowHour?.let {
            binding.textViewHourLow.text = it.toString()
        }
    }

    private fun setHourChange(changeHour: Double?) {
        changeHour?.let {
            val pair = FormatValue.formatValueChange(this, it)
            val text = pair.first
            val color = pair.second
            binding.textViewHourChange.text = text
            binding.textViewHourChange.setTextColor(color)
        }
    }

    private fun setHourChangePct(changePctHour: Double?) {
        changePctHour?.let {
            val pair = FormatValue.formatPct(this, it)
            val text = pair.first
            val color = pair.second
            binding.textViewHourChangePct.text = text
            binding.textViewHourChangePct.setTextColor(color)
        }
    }

    private fun setCoinLogo(imageFullUrl: String) {
        Glide.with(this)
            .load(imageFullUrl)
            .into(binding.imageViewCoinLogo)
    }
}