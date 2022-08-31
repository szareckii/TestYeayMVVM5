package com.zareckii.mvvm5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as MyApp).viewModel
        val timerTV = findViewById<TextView>(R.id.timerTextView)
        val observable = TextObservable()
        observable.observe(object : TextCallback {
            override fun updateText(str: String)  = runOnUiThread {
                timerTV.text = str
            }
        })
        viewModel.init(observable)
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeCounting()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pauseCounting()
    }
}