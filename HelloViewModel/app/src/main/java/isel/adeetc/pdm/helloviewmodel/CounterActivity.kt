package isel.adeetc.pdm.helloviewmodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

const val viewStateKey = "counterValue"

class CounterActivity : AppCompatActivity() {

    private lateinit var viewModel: Counter

    private fun updateUI(counter: Counter) {
        counterView.text = counter.value.toString()
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setContentView(R.layout.activity_main)

        viewModel = savedState?.getParcelable(viewStateKey) ?: Counter(5)
        updateUI(viewModel)

        incButton.setOnClickListener { updateUI(++viewModel) }
        decButton.setOnClickListener { updateUI(--viewModel) }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(viewStateKey, viewModel)
    }
}
