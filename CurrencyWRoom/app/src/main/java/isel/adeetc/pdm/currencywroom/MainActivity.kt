package isel.adeetc.pdm.currencywroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import isel.adeetc.pdm.currencywroom.model.CurrenciesRepository
import isel.adeetc.pdm.currencywroom.model.Quote
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private fun getViewModelFactory(repo: CurrenciesRepository) = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CurrenciesViewModel(this@MainActivity.application as CurrencyApplication, repo) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        currenciesView.setHasFixedSize(true)
        currenciesView.layoutManager = LinearLayoutManager(this)

        val model = ViewModelProviders
                .of(this, getViewModelFactory((application as CurrencyApplication).repo))
                .get(CurrenciesViewModel::class.java)

        currenciesView.adapter = CurrenciesAdapter(model)

        model.currencies.observe(this, Observer<List<Quote>> {
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
