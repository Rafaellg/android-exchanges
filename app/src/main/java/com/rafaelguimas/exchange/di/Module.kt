package com.rafaelguimas.exchange.di

import com.rafaelguimas.exchange.ui.graph.GraphViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exchangeModule = module {
    viewModel { GraphViewModel() }
}