package isel.adeetc.pdm.kotlinx

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(key: String): T {
    return ViewModelProviders.of(this).get(key, T::class.java)
}

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(
        key: String, crossinline create: () -> T): T {

    @Suppress("UNCHECKED_CAST")
    val factory = object : ViewModelProvider.Factory {
        override fun <VM : ViewModel> create(modelClass: Class<VM>): VM = create() as VM
    }

    return ViewModelProviders.of(this, factory).get(key, T::class.java)
}
