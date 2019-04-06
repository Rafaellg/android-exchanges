package com.rafaelguimas.exchange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafaelguimas.exchange.ui.graph.GraphFragment

class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.graph_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GraphFragment.newInstance())
                .commitNow()
        }
    }

}
