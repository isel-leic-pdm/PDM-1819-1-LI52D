package isel.adeetc.pdm.hellolivedata.kotlinx

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}

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