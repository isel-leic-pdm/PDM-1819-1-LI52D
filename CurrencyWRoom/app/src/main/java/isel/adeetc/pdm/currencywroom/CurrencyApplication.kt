package isel.adeetc.pdm.currencywroom

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import isel.adeetc.pdm.currencywroom.model.CurrenciesRepository

class CurrencyApplication : Application() {

    val TAG: String = "CurrencyApp"

    lateinit var queue: RequestQueue
    lateinit var repo: CurrenciesRepository

    override fun onCreate() {
        super.onCreate()
        queue = Volley.newRequestQueue(this)
        repo = CurrenciesRepository(this)
    }
}