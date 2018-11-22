package isel.adeetc.pdm.currencywroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// IMPORTANT: THIS IS A DEMO!
// In production code we NEVER (I MEAN, NEVER) use floating point types for representing financial
// values or transactions. Remember how floating types are represented...

@Entity(tableName = "quotes")
data class Quote(
        @PrimaryKey var name: String,
        var value: Float,
        var date: String
)

// LocalDate and DateTimeFormatter are only supported in API level >= 26 :(
fun Calendar.format(): String = "${this.get(Calendar.YEAR)}-${this.get(Calendar.MONTH)+1}-${this.get(Calendar.DAY_OF_MONTH)}"
