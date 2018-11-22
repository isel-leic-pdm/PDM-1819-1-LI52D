package isel.adeetc.pdm.helloworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import java.util.concurrent.TimeUnit

import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        firstWorkerButton.setOnClickListener {
            val app = applicationContext as Application
            Log.v(app.TAG, "Scheduled FirstWorker with WorkManager on pid ${Process.myPid()}")

            val myConstraints = Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val firstWork = OneTimeWorkRequestBuilder<FirstWorker>()
                .setConstraints(myConstraints)
                .setInitialDelay(15, TimeUnit.SECONDS)
                .build()

            app.workManager.enqueue(firstWork)
            val workInfo = app.workManager.getWorkInfoByIdLiveData(firstWork.id)

            workInfo.observe(this@Main2Activity, Observer<WorkInfo> {
                Log.v(app.TAG, "FirstWorker Observer notified ${it.state} in thread ${Thread.currentThread().name}")
            })
        }

        secondWorkerButton.setOnClickListener {
            val app = applicationContext as Application
            Log.v(app.TAG, "Scheduled SecondWorker with WorkManager on pid ${Process.myPid()}")

            val myConstraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

            val secondWork = OneTimeWorkRequestBuilder<SecondWorker>()
                .setConstraints(myConstraints)
                .setInitialDelay(15, TimeUnit.SECONDS)
                .build()

            app.workManager.enqueue(secondWork)
            val workInfo = app.workManager.getWorkInfoByIdLiveData(secondWork.id)

            workInfo.observe(this@Main2Activity, Observer<WorkInfo> {
                Log.v(app.TAG, "SecondWorker Observer notified ${it.state} in thread ${Thread.currentThread().name}")
            })
        }

        runAllButton.setOnClickListener {
            val app = applicationContext as Application
            Log.v(app.TAG, "Scheduled Work Chain with WorkManager on pid ${Process.myPid()}")

            app.workManager
                .beginWith(OneTimeWorkRequestBuilder<FirstWorker>().build())
                .then(OneTimeWorkRequestBuilder<SecondWorker>().build())
                .enqueue()
        }
    }
}
