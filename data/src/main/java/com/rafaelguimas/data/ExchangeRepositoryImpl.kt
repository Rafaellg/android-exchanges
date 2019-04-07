package com.rafaelguimas.data

import com.rafaelguimas.data.retrofit.RetrofitService
import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.data.ExchangeRepository
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.HistoryModel

class ExchangeRepositoryImpl : ExchangeRepository {

    override suspend fun getExchangesFromPeriod(
        startDate: String,
        endDate: String,
        base: String,
        symbol: String
    ): Result<HistoryModel> {
        return try {
            val historyModel = RetrofitService.getExchangeRatesApi().getExchangeHistory(startDate, endDate, base, symbol).await()

            if (historyModel.rates.isNotEmpty()) {
                Result.Success(historyModel)
            } else {
                Result.Error(Failure.ServerError)
            }
        } catch (exception: Exception) {
            Result.Error(Failure.ServerError)
        }
    }

}