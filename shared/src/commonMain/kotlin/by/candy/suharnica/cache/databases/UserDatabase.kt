package by.candy.suharnica.cache.databases

import by.candy.suharnica.core.dataSource.database.CandyDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow
import sqldelight.User

class UserDatabase(database: CandyDatabase) {

    private val dbQuery = database.userQueries

    internal fun addUser(name: String) {
        dbQuery.addUser(name)
    }

    internal fun getUser(): Flow<User?> {
        return dbQuery.getUser().asFlow().mapToOneOrNull()
    }

    internal fun removeUser() {
        dbQuery.removeUser()
    }

}