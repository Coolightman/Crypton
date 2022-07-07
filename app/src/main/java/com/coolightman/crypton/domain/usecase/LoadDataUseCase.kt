package com.coolightman.crypton.domain.usecase

import com.coolightman.crypton.domain.repository.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.loadData()
}