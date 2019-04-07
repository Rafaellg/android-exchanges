package com.rafaelguimas.exchange.ui.graph

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.rafaelguimas.domain.model.HistoryModel
import com.rafaelguimas.exchange.R
import com.rafaelguimas.exchange.extension.observe
import com.rafaelguimas.exchange.util.DateValueFormatter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btGraphOneYear.setOnClickListener { viewModel.updateGraphMonthPeriod(12) }
        btGraphSixMonths.setOnClickListener { viewModel.updateGraphMonthPeriod(6) }
        btThreeMonths.setOnClickListener { viewModel.updateGraphMonthPeriod(3) }
        btOneMonth.setOnClickListener { viewModel.updateGraphMonthPeriod(1) }
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
            Toast.makeText(context, R.string.error_generic, Toast.LENGTH_LONG).show()
        }

        observe(viewModel.progressLiveData) { progress ->
            progress?.let {
                pbGraphProgress.visibility = if (it) View.VISIBLE else View.GONE
                lcGraphChart.visibility = if (it) View.GONE else View.VISIBLE
            }
        }
    }

    private fun setData(historyModel: HistoryModel) {
        // Create entries list
        val entries = ArrayList<Entry>()
        historyModel.rates.toSortedMap().forEach { period ->
            val dateTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(period.key).time

            period.value.forEach { data ->
                entries.add(Entry(dateTime.toFloat(), data.value.toFloat()))
            }
        }

        // Create dataset with entries
        val dataSet = LineDataSet(entries, getString(R.string.label_dolar))
        dataSet.apply {
            color = ContextCompat.getColor(context as AppCompatActivity, R.color.colorPrimary)
            valueTextColor = Color.WHITE
            setDrawCircles(false)
        }

        // Customize chart colors
        lcGraphChart.xAxis.apply {
            valueFormatter = DateValueFormatter()
            textColor = Color.WHITE
        }
        lcGraphChart.axisLeft.textColor = Color.WHITE
        lcGraphChart.axisRight.textColor = Color.WHITE
        lcGraphChart.legend.textColor = Color.WHITE

        // Set data on graph
        lcGraphChart.data = LineData(dataSet)
        lcGraphChart.invalidate()
    }
}
