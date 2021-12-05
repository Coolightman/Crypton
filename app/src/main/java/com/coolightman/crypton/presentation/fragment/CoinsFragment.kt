package com.coolightman.crypton.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coolightman.crypton.R
import com.coolightman.crypton.databinding.FragmentCoinsBinding
import com.coolightman.crypton.domain.entity.CoinInfo
import com.coolightman.crypton.presentation.adapter.CoinInfoAdapter
import com.coolightman.crypton.presentation.viewmodel.CoinViewModel

class CoinsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    private var _binding: FragmentCoinsBinding? = null
    private val binding: FragmentCoinsBinding
        get() = _binding!!

    private lateinit var coinInfoAdapter: CoinInfoAdapter

    companion object {
        const val NUMBER_OF_COINS = 30
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createObserverForCoins()
        createAdapterForCoins()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun createObserverForCoins() {
        viewModel.coinInfoList.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isNotEmpty()) {
                    val coins = it.takeLast(NUMBER_OF_COINS)
                    coinInfoAdapter.submitList(coins)
                }
            }
        }
    }

    private fun createAdapterForCoins() {
        coinInfoAdapter = CoinInfoAdapter(requireContext()) { onClickCoin(it) }
        binding.recyclerViewCoinPrice.adapter = coinInfoAdapter
        binding.recyclerViewCoinPrice.itemAnimator = null
    }

    private fun onClickCoin(coinInfo: CoinInfo) {
        val fragment = CoinDetailFragment.newInstance(coinInfo.fromSymbol)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}