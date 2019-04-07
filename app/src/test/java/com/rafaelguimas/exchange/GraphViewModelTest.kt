package com.rafaelguimas.exchange

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.rafaelguimas.domain.data.ExchangeRepository
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.use_case.GetHistoryExchangeUseCase
import com.rafaelguimas.exchange.ui.graph.GraphViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ObsoleteCoroutinesApi
class GraphViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val exchangeRepository: ExchangeRepository = mock()
    private val getHistoryExchangeUseCase = GetHistoryExchangeUseCase(exchangeRepository)

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun graphViewModel_updateGraphMonthPeriod_success() = runBlocking {
        val graphViewModel = GraphViewModel(getHistoryExchangeUseCase)

        graphViewModel.updateGraphMonthPeriod(1)

        assertEquals(null, graphViewModel.failureLiveData.value)
        assertEquals(false, graphViewModel.progressLiveData.value)
        assertNotNull(graphViewModel.historyExchangeLiveData.value)
    }

    @Test
    fun graphViewModel_updateGraphMonthPeriod_withError() = runBlocking {
        val graphViewModel = GraphViewModel(getHistoryExchangeUseCase)

        graphViewModel.updateGraphMonthPeriod(-1)

        assertEquals(Failure.ServerError, graphViewModel.failureLiveData.value)
        assertEquals(false, graphViewModel.progressLiveData.value)
        assertEquals(null, graphViewModel.historyExchangeLiveData.value)
    }
}