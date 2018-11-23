package isel.adeetc.pdm.currencyfullapp

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.volley.VolleyError
import isel.adeetc.pdm.currencyfullapp.model.syncSaveTodayQuotesFromDTO
import isel.adeetc.pdm.currencyfullapp.network.syncFetchTodayQuotes

const val NOTIFICATION_ID = 100012

class UpdateQuotesWorker(context : Context, params : WorkerParameters)
    : Worker(context, params) {

    private fun sendNotification(app: CurrencyApplication) {

        val action = PendingIntent.getActivity(app, 101,
                MainActivity.createIntent(app, true), FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(app, app.QUOTES_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(app.getString(R.string.quotes_notification_title))
                .setContentText(app.getString(R.string.quotes_notification_content))
                .setContentIntent(action)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

        NotificationManagerCompat.from(app).notify(NOTIFICATION_ID, notification)
    }

    private fun canRecover(error: VolleyError): Boolean {
        val statusCode =
                if (error.networkResponse != null) error.networkResponse.statusCode
                else 0
        return statusCode in 500..599
    }

    override fun doWork(): Result {
        return try {
            val app = applicationContext as CurrencyApplication
            Log.v(app.TAG, "Updating local DB with today quotes")
            val quotes = syncFetchTodayQuotes(app)
            syncSaveTodayQuotesFromDTO(app, app.db, quotes)
            sendNotification(app)
            Result.SUCCESS
        }
        catch (error: VolleyError) {
            if (canRecover(error)) Result.RETRY else Result.FAILURE
        }
    }
}
