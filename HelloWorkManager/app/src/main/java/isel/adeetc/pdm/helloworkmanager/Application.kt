package isel.adeetc.pdm.helloworkmanager

import androidx.work.WorkManager
import java.util.concurrent.ScheduledThreadPoolExecutor

class Application : android.app.Application() {
    val TAG = "HelloWorkManager"

    lateinit var workManager: WorkManager
    lateinit var threadPoolExecutor: ScheduledThreadPoolExecutor

    override fun onCreate() {
        super.onCreate()
        workManager = WorkManager.getInstance()
        threadPoolExecutor = ScheduledThreadPoolExecutor(2)
    }
}