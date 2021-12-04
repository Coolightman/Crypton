package com.coolightman.crypton.domain.usecase

import com.coolightman.crypton.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.getCoinInfoList()
}