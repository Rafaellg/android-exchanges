package com.rafaelguimas.exchange.ui.graph

import androidx.lifecycle.*
import com.rafaelguimas.domain.model.HistoryModel
import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.use_case.GetHistoryExchangeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GraphViewModel(
    private val getHistoryExchangeUseCase: GetHistoryExchangeUseCase
) : ViewModel(), CoroutineScope, LifecycleObserver {

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    val historyExchangeLiveData = MutableLiveData<HistoryModel>()
    val progressLiveData = MutableLiveData<Boolean>()
    val failureLiveData = MutableLiveData<Failure>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        launch {
            progressLiveData.value = true

            val result = getHistoryExchangeUseCase("2018-10-05", "2019-04-05")

            when (result) {
                is Result.Success -> historyExchangeLiveData.value = result.data
                is Result.Error -> failureLiveData.value = result.failure
            }

            progressLiveData.value = false
        }
    }
}
