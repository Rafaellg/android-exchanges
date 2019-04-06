package com.rafaelguimas.domain.use_case

import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.data.ExchangeRepository
import com.rafaelguimas.domain.model.HistoryModel

class GetHistoryExchangeUseCase(
    private val exchangeRepository: ExchangeRepository
) {

    suspend operator fun invoke(startDate: String, endDate: String): Result<HistoryModel> {
        return exchangeRepository.getExchangesFromPeriod(startDate, endDate, "EUR", "USD")
    }

}