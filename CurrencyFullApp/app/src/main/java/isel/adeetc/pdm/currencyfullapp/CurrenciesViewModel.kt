package isel.adeetc.pdm.currencyfullapp

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import isel.adeetc.pdm.currencyfullapp.model.CurrenciesRepository
import isel.adeetc.pdm.currencyfullapp.model.Quote

class CurrenciesViewModel(
        app: CurrencyApplication,
        private val repo: CurrenciesRepository) : AndroidViewModel(app) {

    val currencies: MutableLiveData<List<Quote>> = MutableLiveData()

    fun updateCurrencies() {
        repo.getCurrencies { currencies.value = it }
    }
}