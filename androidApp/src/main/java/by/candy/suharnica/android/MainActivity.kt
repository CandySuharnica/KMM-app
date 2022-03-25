package by.candy.suharnica.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.candy.suharnica.Greeting
import android.widget.TextView
import by.candy.suharnica.repository.FirebaseCatalogRepo
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.app
import dev.gitlive.firebase.initialize
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.runInterruptible

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        
        runBlocking {
            Log.e("main","start")
            val a = FirebaseCatalogRepo().fetchStatusDetail()
            Log.e("main","finish")
        }

        tv.text = "hello"
    }

   // val a = FirebaseCatalogRepo().fetchStatusDetail()

        //FirebaseApp
    //initialize(context = this)

}

