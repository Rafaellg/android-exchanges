package com.rafaelguimas.exchange

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat


class DateValueFormatter : ValueFormatter() {

    private val mFormat: SimpleDateFormat = SimpleDateFormat("MMM dd")

    override fun getFormattedValue(value: Float): String {
        return mFormat.format(value)
    }
}