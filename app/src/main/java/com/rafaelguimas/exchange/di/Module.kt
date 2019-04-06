package com.rafaelguimas.exchange.di

import com.rafaelguimas.data.ExchangeRepositoryImpl
import com.rafaelguimas.domain.data.ExchangeRepository
import com.rafaelguimas.domain.use_case.GetHistoryExchangeUseCase
import com.rafaelguimas.exchange.ui.graph.GraphViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { GraphViewModel(get()) }
}

val domainModule = module {
    single { GetHistoryExchangeUseCase(get()) }
}

val dataModule = module {
    single { ExchangeRepositoryImpl() as ExchangeRepository }
}