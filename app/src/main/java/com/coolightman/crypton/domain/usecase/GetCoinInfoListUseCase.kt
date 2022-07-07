package com.coolightman.crypton.domain.usecase

import com.coolightman.crypton.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.getCoinInfoList()
}