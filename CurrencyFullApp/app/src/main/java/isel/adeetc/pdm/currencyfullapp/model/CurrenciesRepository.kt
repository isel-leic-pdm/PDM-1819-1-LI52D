package isel.adeetc.pdm.currencyfullapp.model

import android.util.Log
import isel.adeetc.pdm.currencyfullapp.CurrencyApplication
import isel.adeetc.pdm.currencyfullapp.kotlinx.AsyncWork
import isel.adeetc.pdm.currencyfullapp.kotlinx.runAsync
import isel.adeetc.pdm.currencyfullapp.network.CurrenciesDTO
import isel.adeetc.pdm.currencyfullapp.network.fetchTodayQuotes
import java.util.*

fun syncSaveTodayQuotesFromDTO(app: CurrencyApplication, db: CurrenciesDatabase, dto: CurrenciesDTO): List<Quote> {
    Log.v(app.TAG, "Saving quotes to DB")
    val date = Calendar.getInstance()
    val result = dto.quotes.map { Quote(it.currency, it.quote, date) }
    db.quoteDAO().insertAll(*result.toTypedArray())
    return result
}

class CurrenciesRepository(
        private val app: CurrencyApplication,
        private val db: CurrenciesDatabase) {

    private fun saveToDB(dto: CurrenciesDTO): AsyncWork<List<Quote>> {
        return runAsync {
            if (dto.success) syncSaveTodayQuotesFromDTO(app, db, dto)
            else listOf()
        }
    }

    fun getCurrencies(success: (List<Quote>) -> Unit) {
        runAsync {
            db.quoteDAO().getAllByDate(Calendar.getInstance())
        }.andThen { quotes ->
            if (quotes.isEmpty()) fetchTodayQuotes(app, { saveToDB(it).andThen(success) }, { })
            else { Log.v(app.TAG, "Got quotes from DB"); success(quotes) }
        }
    }
}

