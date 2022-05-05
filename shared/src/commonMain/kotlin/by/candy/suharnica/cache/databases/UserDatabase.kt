package by.candy.suharnica.cache.databases

import by.candy.suharnica.core.dataSource.database.CandyDatabase
import com.squareup.sqldelight.internal.copyOnWriteList
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrDefault
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import sqldelight.GetLikes
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

    internal fun like(id: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            val oldListOfLikesFlow = getLikes().first()
            if (oldListOfLikesFlow != null) {
                val oldListOfLikes = oldListOfLikesFlow.likes ?: emptyList()

                val newListOfLikes = oldListOfLikes.toMutableList()

                if (newListOfLikes.contains(id)) newListOfLikes.remove(id)
                else newListOfLikes.add(id)

                dbQuery.like(newListOfLikes)

            } else {
                dbQuery.createLikeTable(listOf(id))
            }
        }
    }

    internal fun getLikes(): Flow<GetLikes?> {
        return dbQuery.getLikes().asFlow().mapToOneOrNull()
    }
}