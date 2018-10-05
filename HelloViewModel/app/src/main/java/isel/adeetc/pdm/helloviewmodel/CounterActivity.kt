package isel.adeetc.pdm.helloviewmodel

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import isel.adeetc.pdm.kotlinx.getViewModel
import kotlinx.android.synthetic.main.activity_main.*

const val counter = "counterValue"

class CounterActivity : AppCompatActivity() {

    private lateinit var viewModel: Counter

    private fun updateUI(counter: Counter) {
        counterView.text = counter.value.toString()
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setContentView(R.layout.activity_main)

        viewModel = this.getViewModel(counter)
        updateUI(viewModel)

        incButton.setOnClickListener { updateUI(++viewModel) }
        decButton.setOnClickListener { updateUI(--viewModel) }
    }
}
