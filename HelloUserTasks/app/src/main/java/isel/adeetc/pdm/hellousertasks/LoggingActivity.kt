package isel.adeetc.pdm.hellousertasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

const val LifeCycleLoggingTag = "LifeCycle"

abstract class LoggingActivity(private val name: String) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(LifeCycleLoggingTag, "$name: onCreate on " + hashCode())
    }

    override fun onStart() {
        super.onStart()
        Log.v(LifeCycleLoggingTag, "$name: onStart on " + hashCode())
    }

    override fun onResume() {
        super.onResume()
        Log.v(LifeCycleLoggingTag, "$name: onResume on " + hashCode())
    }

    override fun onPause() {
        super.onPause()
        Log.v(LifeCycleLoggingTag, "$name: onPause on " + hashCode())
    }

    override fun onStop() {
        super.onStop()
        Log.v(LifeCycleLoggingTag, "$name: onStop on " + hashCode())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(LifeCycleLoggingTag, "$name: onDestroy on " + hashCode())
    }
}