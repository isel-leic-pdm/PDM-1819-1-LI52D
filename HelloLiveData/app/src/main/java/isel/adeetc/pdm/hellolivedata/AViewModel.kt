package isel.adeetc.pdm.hellolivedata

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AViewModel: ViewModel() {

    private class AsyncWork(val resultHolder: MutableLiveData<String>) : AsyncTask<Unit, Unit, String>() {
        override fun doInBackground(vararg params: Unit?): String {
            Log.v("HelloLiveData", "Thread ${Thread.currentThread().id} is working hard")
            Thread.sleep(10000)
            Log.v("HelloLiveData", "Thread ${Thread.currentThread().id} completed hard work")
            return "Work Completed"
        }

        override fun onPostExecute(result: String?) {
            resultHolder.value = result
        }
    }

    val data: MutableLiveData<String> = MutableLiveData<String>()

    fun updateData() {
        val work = AsyncWork(data)
        Log.v("HelloLiveData", "Thread ${Thread.currentThread().id} dispatching work")
        work.execute()
    }
}
