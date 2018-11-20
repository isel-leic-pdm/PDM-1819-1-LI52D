package isel.adeetc.pdm.currencywroom

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class CurrencyApplication : Application() {

    lateinit var queue: RequestQueue
    lateinit var repo: CurrenciesRepository

    override fun onCreate() {
        super.onCreate()
        queue = Volley.newRequestQueue(this)
        repo = CurrenciesRepository(this)
    }
}