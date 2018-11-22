package isel.adeetc.pdm.helloworkmanager

import android.content.Context
import android.os.Process
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

const val KEY_RESULT = "FirstWorkerResult"

class FirstWorker(context : Context, params : WorkerParameters)
    : Worker(context, params) {

    override fun doWork(): Result {
        val app = applicationContext as Application
        Log.v(app.TAG, "FirstWorker running on thread ${Thread.currentThread().name} on pid ${Process.myPid()}")
        Thread.sleep(10000)
        outputData = Data.Builder()
            .putString(KEY_RESULT, "The result of the FirstWorker")
            .build()
        Log.v(app.TAG, "FirstWorker Work completed successfully")
        return Result.SUCCESS
    }
}

class SecondWorker(context : Context, params : WorkerParameters)
    : Worker(context, params) {

    override fun doWork(): Result {
        val app = applicationContext as Application
        Log.v(app.TAG, "SecondWorker running on thread ${Thread.currentThread().name} on pid ${Process.myPid()}")
        val input: String? = inputData.getString(KEY_RESULT)
        if (input != null)
            Log.v(app.TAG, "SecondWorker received input $input")
        else
            Log.v(app.TAG, "SecondWorker received no input")
        Thread.sleep(10000)
        Log.v(app.TAG, "SecondWorker Work completed successfully")
        return Result.SUCCESS
    }
}