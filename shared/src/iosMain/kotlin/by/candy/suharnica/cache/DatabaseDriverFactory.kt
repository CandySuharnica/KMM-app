package by.candy.suharnica.cache

import by.candy.suharnica.core.dataSource.database.CandyDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(CandyDatabase.Schema, "candy.db")
    }
}