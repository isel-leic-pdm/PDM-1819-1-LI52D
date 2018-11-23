package isel.adeetc.pdm.currencyfullapp.model

import androidx.room.*
import java.util.*

@Dao
interface QuoteDAO {
    @Query("SELECT * FROM quotes")
    fun getAll(): List<Quote>

    @Query("SELECT * FROM quotes WHERE date LIKE :date")
    fun getAllByDate(date: Calendar): List<Quote>

    @Query("SELECT * FROM quotes WHERE name = :name")
    fun findById(name: String): Quote

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg quotes: Quote)

    @Delete
    fun delete(quote: Quote)
}

@Database(entities = arrayOf(Quote::class), version = 1)
@TypeConverters(QuoteTypeConverters::class)
abstract class CurrenciesDatabase : RoomDatabase() {
    abstract fun quoteDAO(): QuoteDAO
}