package com.rafaelguimas.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelguimas.domain.data.ExchangeRepository
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.HistoryModel
import com.rafaelguimas.domain.use_case.GetHistoryExchangeUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetHistoryExchangeUseCaseTest {

    private val historyModel = HistoryModel.dummy()
    private val exchangeRepository: ExchangeRepository = mock()
    private val getHistoryExchangeUseCase = GetHistoryExchangeUseCase(exchangeRepository)

    @Test
    fun getHistoryExchange_success() = runBlocking {
        val expected = Result.Success(historyModel)

        whenever(exchangeRepository.getExchangesFromPeriod("2018-01-01", "2019-01-01", "EUR", "USD")).thenReturn(
            expected
        )

        val result = getHistoryExchangeUseCase("2018-01-01", "2019-01-01")

        assertEquals(Result.Success(historyModel), result)
    }

    @Test
    fun getHistoryExchange_withError() = runBlocking {
        val expected = Result.Error(Failure.ServerError)
        whenever(exchangeRepository.getExchangesFromPeriod("2019-01-01", "2018-01-01", "EUR", "USD")).thenReturn(
            expected
        )

        val result = getHistoryExchangeUseCase("2019-01-01", "2018-01-01")

        assertEquals(expected, result)
    }
}