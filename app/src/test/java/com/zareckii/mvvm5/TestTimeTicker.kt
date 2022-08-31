package com.zareckii.mvvm5

class TestTimeTicker: TimeTicker {

    private var callback: TimeTicker.Callback? = null

    var state = 0

    override fun start(callback: TimeTicker.Callback, period: Long) {
        this.callback = callback
        state = 1
    }

    override fun stop() {
        callback = null
        state = -1
    }

    fun tick(times: Int) {
        for (i in 0 until times)
            callback?.tick()

    }
}