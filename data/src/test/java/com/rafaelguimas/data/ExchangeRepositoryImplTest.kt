package com.rafaelguimas.data

import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.exception.Failure
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExchangeRepositoryImplTest {

    @Test
    fun exchangeRepositoryImpl_success() = runBlocking {
        val repository = ExchangeRepositoryImpl()

        val result = repository.getExchangesFromPeriod("2018-01-01", "2019-01-01", "EUR", "USD")

        assertTrue(result is Result.Success)
    }

    @Test
    fun exchangeRepositoryImpl_withError() = runBlocking {
        val repository = ExchangeRepositoryImpl()

        val result = repository.getExchangesFromPeriod("", "", "123", "USD")

        assertEquals(Result.Error(Failure.ServerError), result)
    }
}