package com.rafaelguimas.data

import com.rafaelguimas.domain.model.HistoryModel
import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.data.ExchangeRepository
import com.rafaelguimas.domain.exception.Failure

class ExchangeRepositoryImpl : ExchangeRepository {

    override suspend fun getExchangesFromPeriod(startDate: String, endDate: String, base: String, symbol: String): Result<HistoryModel> {
        return try {
            val historyModel = RetrofitService.getExchangeRatesApi().getEurHistory(startDate, endDate, base, symbol).await()
            Result.Success(historyModel)
        } catch (exception: Exception) {
            Result.Error(Failure.ServerError)
        }
    }

}