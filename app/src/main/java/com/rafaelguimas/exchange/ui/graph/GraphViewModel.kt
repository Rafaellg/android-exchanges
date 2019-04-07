package com.rafaelguimas.exchange.ui.graph

import androidx.lifecycle.*
import com.rafaelguimas.domain.Result
import com.rafaelguimas.domain.exception.Failure
import com.rafaelguimas.domain.model.HistoryModel
import com.rafaelguimas.domain.use_case.GetHistoryExchangeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class GraphViewModel(
    private val getHistoryExchangeUseCase: GetHistoryExchangeUseCase
) : ViewModel(), CoroutineScope, LifecycleObserver {
    
    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd"
    }

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    val historyExchangeLiveData = MutableLiveData<HistoryModel>()
    val progressLiveData = MutableLiveData<Boolean>()
    val failureLiveData = MutableLiveData<Failure>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        updateGraphMonthPeriod(12)
    }

    fun updateGraphMonthPeriod(months: Int) {
        launch {
            progressLiveData.value = true

            val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
            val currentDate = LocalDateTime.now().format(formatter) // Current
            val startDate = LocalDateTime.now().minusMonths(months.toLong()).format(formatter)

            val result = getHistoryExchangeUseCase(startDate, currentDate)
            when (result) {
                is Result.Success -> historyExchangeLiveData.value = result.data
                is Result.Error -> failureLiveData.value = result.failure
            }

            progressLiveData.value = false
        }
    }
}
