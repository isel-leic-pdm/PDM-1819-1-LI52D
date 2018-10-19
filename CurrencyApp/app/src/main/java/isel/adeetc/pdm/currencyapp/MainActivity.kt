package isel.adeetc.pdm.currencyapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import isel.adeetc.pdm.currencyapp.network.Currencies
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private fun getViewModelFactory() = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CurrenciesViewModel(this@MainActivity.application as CurrencyApplication) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        currenciesView.setHasFixedSize(true)
        currenciesView.layoutManager = LinearLayoutManager(this)

        val model = ViewModelProviders
                .of(this, getViewModelFactory())
                .get(CurrenciesViewModel::class.java)

        currenciesView.adapter = CurrenciesAdapter(model)

        model.currencies.observe(this, Observer<Currencies> {
            currenciesView.adapter = CurrenciesAdapter(model)
        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, R.string.action_title, Snackbar.LENGTH_LONG)
                    .setAction(R.string.action_name) {
                        model.updateCurrencies()
                    }.show()
        }

    }
}
