package isel.adeetc.pdm.currencyfullapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

// IMPORTANT: THIS IS A DEMO!
// In production code we NEVER (I MEAN, NEVER) use floating point types for representing financial
// values or transactions. Remember how floating types are represented...

@Entity(tableName = "quotes", primaryKeys = arrayOf("name", "date"))
data class Quote(
        var name: String,
        var value: Float,
        var date: Calendar
)

class QuoteTypeConverters {

    @TypeConverter
    fun dateFromString(value: String): Calendar {
        val date = Calendar.getInstance().apply {
            set(Calendar.YEAR, value.split('-').get(0).toInt())
            set(Calendar.MONTH, value.split('-').get(1).toInt() - 1)
            set(Calendar.DAY_OF_MONTH, value.split('-').get(2).toInt())
        }
        return date
    }

    @TypeConverter
    fun dateToString(value: Calendar) = "${value.get(Calendar.YEAR)}-" +
            "${value.get(Calendar.MONTH)+1}-" +
            "${value.get(Calendar.DAY_OF_MONTH)}"
}
