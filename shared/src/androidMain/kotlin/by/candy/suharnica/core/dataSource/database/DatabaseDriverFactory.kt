package by.candy.suharnica.core.dataSource.database

import android.content.Context
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CandyDatabase.Schema, context, "candy.db")
    }
}