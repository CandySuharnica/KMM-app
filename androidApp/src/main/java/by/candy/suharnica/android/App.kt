package by.candy.suharnica.android

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.initialize

class App : Application() {

    private var database: FirebaseApp? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Firebase.initialize(this)
    }

    fun getDatabase() = database

    companion object {
        var instance: App? = null
    }

}