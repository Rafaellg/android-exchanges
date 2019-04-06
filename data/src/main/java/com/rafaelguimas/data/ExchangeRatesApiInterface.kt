package com.rafaelguimas.data

import com.rafaelguimas.domain.model.HistoryModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApiInterface {
    @GET("/history")
    fun getEurHistory(
        @Query("start_at") startAt: String,
        @Query("end_at") endAt: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Deferred<HistoryModel>
}