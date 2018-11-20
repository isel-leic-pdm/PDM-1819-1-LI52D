package isel.adeetc.pdm.currencywroom

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import isel.adeetc.pdm.currencywroom.network.Currencies

class CurrenciesViewModel(
        private val app: CurrencyApplication,
        private val repo: CurrenciesRepository) : AndroidViewModel(app) {

    val currencies: MutableLiveData<Currencies> = MutableLiveData()

    fun updateCurrencies() {
        repo.getCurrencies(
                { currencies.value = it },
                { Toast.makeText(app,R.string.error_network, Toast.LENGTH_LONG).show() }
            )
    }
}