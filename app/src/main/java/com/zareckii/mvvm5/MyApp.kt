package com.zareckii.mvvm5

import android.app.Application

class MyApp: Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(Model(CacheDataSource(this), TimerTicker()))
    }
}