package isel.adeetc.pdm.currencywroom

import com.android.volley.Response
import isel.adeetc.pdm.currencywroom.network.Currencies
import isel.adeetc.pdm.currencywroom.network.GetRequest

class CurrenciesRepository(private val app: CurrencyApplication) {

    fun getCurrencies(success: (Currencies) -> Unit, error: () -> Unit) {
        val queue = app.queue
        val url = "http://apilayer.net/api/live" +
                "?access_key=a76c13f17a0d445356c0f4d36738fe92" +
                "&source=USD" +
                "&format=1"

        val request = GetRequest(url,
                Response.Listener { success(it) },
                Response.ErrorListener { error() })

        queue.add(request)
    }
}