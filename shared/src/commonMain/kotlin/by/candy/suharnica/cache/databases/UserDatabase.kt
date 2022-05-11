package by.candy.suharnica.cache.databases

import by.candy.suharnica.core.dataSource.database.CandyDatabase
import com.squareup.sqldelight.runtime.coroutines.*
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

    internal fun getUser(): Flow<List<User>> {
        return dbQuery.getUser().asFlow().mapToList()
    }

    internal fun removeUser() {
        dbQuery.removeUser()
    }

    internal fun like(id: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            val oldListOfLikesFlow = getLikes().first().firstNotNullOfOrNull { it }
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

    internal fun getLikes(): Flow<List<GetLikes>> {
        return dbQuery.getLikes().asFlow().mapToList()
    }
}