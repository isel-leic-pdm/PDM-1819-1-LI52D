package isel.adeetc.pdm.kotlinx

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(key: String): T {
    return ViewModelProviders.of(this).get(key, T::class.java)
}
