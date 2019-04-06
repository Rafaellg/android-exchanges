package com.rafaelguimas.exchange.ui.graph

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.rafaelguimas.domain.extension.observe
import com.rafaelguimas.domain.model.HistoryModel
import com.rafaelguimas.exchange.DateValueFormatter
import com.rafaelguimas.exchange.R
import kotlinx.android.synthetic.main.graph_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class GraphFragment : Fragment() {

    companion object {
        fun newInstance() = GraphFragment()
    }

    private val viewModel: GraphViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.graph_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycle.addObserver(viewModel)

        observe(viewModel.historyExchangeLiveData) { historyModel ->
            historyModel?.let {
                setData(it)
            }
        }

        observe(viewModel.failureLiveData) {
            Toast.makeText(context, "Something went wrong. =/", Toast.LENGTH_LONG).show()
        }

        observe(viewModel.progressLiveData) { progress ->
            progress?.let {
                pbGraphProgress.visibility = if (it) View.VISIBLE else View.GONE
                lcGraphChart.visibility = if (it) View.GONE else View.VISIBLE
            }
        }
    }

    private fun setData(historyModel: HistoryModel) {
        val entries = ArrayList<Entry>()

        historyModel.rates.toSortedMap().forEach { period ->
            val dateTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(period.key).time

            period.value.forEach { data ->
                entries.add(Entry(dateTime.toFloat(), data.value.toFloat()))
            }
        }

        val dataSet = LineDataSet(entries, "Dolar")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK

        lcGraphChart.xAxis.valueFormatter = DateValueFormatter()

        val lineData = LineData(dataSet)
        lcGraphChart.data = lineData
        lcGraphChart.invalidate()
    }

    private fun setExampleData() {
        val entries = ArrayList<Entry>()

        entries.add(Entry(1f, 1f))
        entries.add(Entry(2f, 9f))
        entries.add(Entry(3f, 5f))
        entries.add(Entry(4f, 2f))
        entries.add(Entry(5f, 6f))
        entries.add(Entry(6f, 3f))
        entries.add(Entry(7f, 1f))

        val dataSet = LineDataSet(entries, "Label")
        dataSet.color = Color.BLUE;
        dataSet.valueTextColor = Color.BLACK

        val lineData = LineData(dataSet)
        lcGraphChart.data = lineData
        lcGraphChart.invalidate()
    }
}
