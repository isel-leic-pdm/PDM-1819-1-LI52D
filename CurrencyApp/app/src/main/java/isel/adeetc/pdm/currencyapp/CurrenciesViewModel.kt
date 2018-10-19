package isel.adeetc.pdm.currencyapp

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Response
import isel.adeetc.pdm.currencyapp.network.Currencies
import isel.adeetc.pdm.currencyapp.network.GetRequest

class CurrenciesViewModel(app: CurrencyApplication) : AndroidViewModel(app) {

    val currencies: MutableLiveData<Currencies> = MutableLiveData()

    fun updateCurrencies() {
        val queue = getApplication<CurrencyApplication>().queue
        val url = "http://apilayer.net/api/live" +
                "?access_key=a76c13f17a0d445356c0f4d36738fe92" +
                "&source=USD" +
                "&format=1"

        val request = GetRequest(url,
                Response.Listener { currencies.value = it },
                Response.ErrorListener {
                    Toast.makeText(getApplication(), R.string.error_network, Toast.LENGTH_LONG).show()
                })

        queue.add(request)
    }
}