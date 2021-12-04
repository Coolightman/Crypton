package com.coolightman.crypton.domain.usecase

import com.coolightman.crypton.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}