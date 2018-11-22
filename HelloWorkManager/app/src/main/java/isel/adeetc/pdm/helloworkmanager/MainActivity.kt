package isel.adeetc.pdm.helloworkmanager

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private fun scheduleWorkWithThreadPool() {
        val scheduler = (application as Application).threadPoolExecutor
        val app = applicationContext as Application
        val work = {
            Log.v(app.TAG, "Work running on thread ${Thread.currentThread().name} on pid ${Process.myPid()}")
            Thread.sleep(10000)
            Log.v(app.TAG, "Work completed successfully")
        }
        scheduler.schedule(work, 15, TimeUnit.SECONDS)

        Log.v(app.TAG, "Scheduled Work with a ScheduledThreadPoolExecutor on pid ${Process.myPid()}")
    }

    private fun scheduleWorkWithWorkManager() {
        val workManager = (application as Application).workManager
        val firstWork = OneTimeWorkRequestBuilder<FirstWorker>()
            .setInitialDelay(15, TimeUnit.SECONDS)
            .build()
        workManager.enqueue(firstWork)

        val app = applicationContext as Application
        Log.v(app.TAG, "Scheduled FirstWorker with WorkManager on pid ${Process.myPid()}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workerButton.setOnClickListener {
            scheduleWorkWithThreadPool()
            scheduleWorkWithWorkManager()
        }

        round2Button.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
    }
}
