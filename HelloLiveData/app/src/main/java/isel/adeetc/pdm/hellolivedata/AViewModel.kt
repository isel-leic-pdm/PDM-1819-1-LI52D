package isel.adeetc.pdm.hellolivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import isel.adeetc.pdm.hellolivedata.kotlinx.runAsync

class AViewModel: ViewModel() {

    val data: MutableLiveData<String> = MutableLiveData()

    fun updateData() {
        runAsync {
            Thread.sleep(10000)
            "WorkCompleted"
        } andThen {
            data.value = it
        }
    }
}
