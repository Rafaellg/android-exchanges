package com.rafaelguimas.exchange

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.rafaelguimas.exchange.di.dataModule
import com.rafaelguimas.exchange.di.domainModule
import com.rafaelguimas.exchange.di.presentationModule
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        startKoin {
            modules(
                presentationModule,
                domainModule,
                dataModule
            )
        }
    }
}