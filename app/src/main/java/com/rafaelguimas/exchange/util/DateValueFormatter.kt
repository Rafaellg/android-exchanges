package com.rafaelguimas.exchange.util

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class DateValueFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return SimpleDateFormat("MMM dd", Locale.getDefault()).format(value)
    }

}