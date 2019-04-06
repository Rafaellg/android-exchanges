package com.rafaelguimas.domain.data

import com.rafaelguimas.domain.model.HistoryModel
import com.rafaelguimas.domain.Result

interface ExchangeRepository {
    suspend fun getExchangesFromPeriod(startDate: String, endDate: String, base: String, symbol: String): Result<HistoryModel>
}