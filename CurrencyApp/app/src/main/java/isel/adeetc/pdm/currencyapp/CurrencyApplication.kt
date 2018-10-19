package isel.adeetc.pdm.currencyapp

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class CurrencyApplication : Application() {

    lateinit var queue: RequestQueue

    override fun onCreate() {
        super.onCreate()
        queue = Volley.newRequestQueue(this)
    }
}