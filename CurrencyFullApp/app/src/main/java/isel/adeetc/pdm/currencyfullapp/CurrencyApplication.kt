package isel.adeetc.pdm.currencyfullapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.work.*
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import isel.adeetc.pdm.currencyfullapp.model.CurrenciesDatabase
import isel.adeetc.pdm.currencyfullapp.model.CurrenciesRepository
import java.util.concurrent.TimeUnit

class CurrencyApplication : Application() {

    val TAG: String = "CurrencyApp"
    val QUOTES_NOTIFICATION_CHANNEL_ID: String = "QuotesNotificationChannelId"
    val DB_UPDATE_JOB_ID : String = "DB_UPDATE_JOB_ID"

    lateinit var queue: RequestQueue
    lateinit var repo: CurrenciesRepository
    lateinit var db: CurrenciesDatabase
    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        queue = Volley.newRequestQueue(this)

        db = Room.databaseBuilder(this, CurrenciesDatabase::class.java, "quotes-db").build()

        repo = CurrenciesRepository(this, db)
        workManager = WorkManager.getInstance()

        createNotificationChannels()
        scheduleDBUpdate()
    }

    private fun scheduleDBUpdate() {

        val updateRequest = PeriodicWorkRequestBuilder<UpdateQuotesWorker>(
                1, TimeUnit.DAYS)
                .setConstraints(Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.UNMETERED)
                        .setRequiresCharging(true)
                        .build())
                .build()

        workManager.enqueueUniquePeriodicWork(DB_UPDATE_JOB_ID, ExistingPeriodicWorkPolicy.KEEP, updateRequest)
    }

    private fun createNotificationChannels() {

        // Create notification channel if we are running on a O+ device
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    QUOTES_NOTIFICATION_CHANNEL_ID,
                    getString(R.string.quotes_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = getString(R.string.quotes_channel_description)
            }

            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }
}