package isel.adeetc.pdm.currencywroom.model

import android.util.Log
import androidx.room.Room
import com.android.volley.Response
import isel.adeetc.pdm.currencywroom.CurrencyApplication
import isel.adeetc.pdm.currencywroom.kotlinx.AsyncWork
import isel.adeetc.pdm.currencywroom.kotlinx.runAsync
import isel.adeetc.pdm.currencywroom.network.CurrenciesDTO
import isel.adeetc.pdm.currencywroom.network.GetRequest
import java.util.*

class CurrenciesRepository(private val app: CurrencyApplication) {

    private val db = Room
            .databaseBuilder(app, CurrenciesDatabase::class.java, "quotes-db")
            .build()

    private fun saveToDB(dto: CurrenciesDTO): AsyncWork<List<Quote>> {
        return runAsync {
            if (dto.success) {
                Log.v(app.TAG, "Saving quotes to DB")
                val date = Calendar.getInstance().format()
                val result = dto.quotes.map { Quote(it.currency, it.quote, date) }
                db.quoteDAO().insertAll(*result.toTypedArray())
                result
            }
            else listOf()
        }
    }

    private fun fetchDataFromAPI(success: (CurrenciesDTO) -> Unit) {

        Log.v(app.TAG, "Fetching quotes from API")
        val queue = app.queue
        val url = "http://apilayer.net/api/live" +
                "?access_key=a76c13f17a0d445356c0f4d36738fe92" +
                "&source=USD" +
                "&format=1"

        val request = GetRequest(url,
                Response.Listener { success(it) },
                Response.ErrorListener {
                    Log.e(app.TAG, "Could not get quotes from API", it)
                })

        queue.add(request)
    }

    fun getCurrencies(success: (List<Quote>) -> Unit) {
        runAsync {
            db.quoteDAO().getAllByDate(Calendar.getInstance().format())
        }.andThen {
            if (it.isEmpty()) fetchDataFromAPI { saveToDB(it).andThen(success) }
            else {
                Log.v(app.TAG, "Got quotes from DB")
                success(it)
            }
        }
    }
}