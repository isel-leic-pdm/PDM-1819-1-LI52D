package isel.adeetc.pdm.hellothreadingmodel

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("HelloThreadingModel", "onCreate for ${hashCode()} on Thread ${Thread.currentThread().id} is working hard")

        hitMeButton.setOnClickListener {
            val work = object: AsyncTask<Unit, Unit, String>() {
                override fun doInBackground(vararg params: Unit?): String {
                    Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} is working hard")
                    Thread.sleep(10000)
                    Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} completed hard work")
                    return "Work Completed"
                }

                override fun onPostExecute(result: String?) {
                    Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} is displaying result")
                    Log.v("HelloThreadingModel", "Activity is ${this@MainActivity.hashCode()}")
                    msgTextView.text = result
                }
            }

            Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} dispatching work")
            work.execute()
            Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} dispatched work")
        }
    }
}
