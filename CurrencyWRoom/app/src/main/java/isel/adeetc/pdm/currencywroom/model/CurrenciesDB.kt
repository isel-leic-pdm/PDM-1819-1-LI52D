package isel.adeetc.pdm.currencywroom.model

import androidx.room.*

@Dao
interface QuoteDAO {
    @Query("SELECT * FROM quotes")
    fun getAll(): List<Quote>

    @Query("SELECT * FROM quotes WHERE date LIKE :date")
    fun getAllByDate(date: String): List<Quote>

    @Query("SELECT * FROM quotes WHERE name = :name")
    fun findById(name: String): Quote

    @Insert
    fun insertAll(vararg quotes: Quote)

    @Delete
    fun delete(quote: Quote)
}

@Database(entities = arrayOf(Quote::class), version = 1)
abstract class CurrenciesDatabase : RoomDatabase() {
    abstract fun quoteDAO(): QuoteDAO
}