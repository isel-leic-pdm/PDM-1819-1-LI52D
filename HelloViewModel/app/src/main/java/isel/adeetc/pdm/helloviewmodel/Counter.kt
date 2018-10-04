package isel.adeetc.pdm.helloviewmodel

import android.os.Parcel
import android.os.Parcelable

data class Counter(val modulo: Int, val value: Int = 0): Parcelable {

    companion object CREATOR : Parcelable.Creator<Counter> {
        override fun createFromParcel(parcel: Parcel) = Counter(parcel.readInt(), parcel.readInt())
        override fun newArray(size: Int): Array<Counter?> = arrayOfNulls(size)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(modulo)
        dest?.writeInt(value)
    }

    override fun describeContents() = 0

    operator fun inc(): Counter = Counter(modulo, (value + 1) % modulo)
    operator fun dec(): Counter = Counter(modulo, if (value == 0) modulo - 1 else value - 1)
}