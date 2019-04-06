package com.rafaelguimas.domain.model

data class HistoryModel(
    val base: String,
    val rates: HashMap<String, HashMap<String, Double>>
) {
    companion object {
        fun random() = HistoryModel(
            "EUR",
            hashMapOf(
                Pair("2018-02-22", hashMapOf(Pair("USD", 1.2276))),
                Pair("2018-02-08", hashMapOf(Pair("USD", 1.2252))),
                Pair("2018-05-18", hashMapOf(Pair("USD", 1.1781)))
            )
        )
    }
}