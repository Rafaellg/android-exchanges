package com.rafaelguimas.exchange.ui.graph

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.rafaelguimas.exchange.R
import kotlinx.android.synthetic.main.graph_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


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


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData() {
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
        lcGraph.data = lineData
        lcGraph.invalidate()
    }
}
