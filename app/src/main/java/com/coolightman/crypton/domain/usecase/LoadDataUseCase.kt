package com.coolightman.crypton.domain.usecase

import com.coolightman.crypton.domain.repository.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.loadData()
}