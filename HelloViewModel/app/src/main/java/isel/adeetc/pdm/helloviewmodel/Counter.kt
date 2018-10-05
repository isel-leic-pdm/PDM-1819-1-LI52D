package isel.adeetc.pdm.helloviewmodel

import android.arch.lifecycle.ViewModel

data class Counter(val modulo: Int=10, var value: Int = 0): ViewModel() {

    operator fun inc(): Counter {
        value = (value + 1) % modulo
        return this
    }

    operator fun dec(): Counter {
        value = if (value == 0) modulo - 1 else value - 1
        return this
    }
}