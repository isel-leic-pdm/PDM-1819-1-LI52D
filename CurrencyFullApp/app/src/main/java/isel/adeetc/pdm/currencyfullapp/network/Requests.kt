package isel.adeetc.pdm.currencyfullapp.network

import android.util.Log
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.RequestFuture
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import isel.adeetc.pdm.currencyfullapp.CurrencyApplication


const val ENDPOINT_URL = "http://apilayer.net/api/live" +
        "?access_key=a76c13f17a0d445356c0f4d36738fe92" +
        "&source=USD" +
        "&format=1"

private class GetRequest(url: String, success: Response.Listener<CurrenciesDTO>, error: Response.ErrorListener)
    : JsonRequest<CurrenciesDTO>(Request.Method.GET, url, "", success, error) {

    override fun parseNetworkResponse(response: NetworkResponse): Response<CurrenciesDTO> {
        val mapper = jacksonObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val currenciesDto = mapper.readValue(String(response.data), CurrenciesDTO::class.java)
        return Response.success(currenciesDto, null)
    }
}

fun fetchTodayQuotes(app: CurrencyApplication,
                     success: (CurrenciesDTO) -> Unit,
                     failure: (VolleyError) -> Unit) {

    Log.v(app.TAG, "Fetching quotes from API")
    val request = GetRequest(ENDPOINT_URL,
            Response.Listener { success(it) },
            Response.ErrorListener {
                Log.e(app.TAG, "Could not get quotes from API", it)
                failure(it)
            })

    app.queue.add(request)
}

fun syncFetchTodayQuotes(app: CurrencyApplication): CurrenciesDTO {

    Log.v(app.TAG, "Fetching quotes from API")
    val future: RequestFuture<CurrenciesDTO> = RequestFuture.newFuture()
    app.queue.add(GetRequest(ENDPOINT_URL, future, future))
    return future.get()
}