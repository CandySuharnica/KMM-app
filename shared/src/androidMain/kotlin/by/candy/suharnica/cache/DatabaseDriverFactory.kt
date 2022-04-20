package by.candy.suharnica.cache

import android.content.Context
import by.candy.suharnica.core.dataSource.database.CandyDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CandyDatabase.Schema, context, "candy.db")
    }
}