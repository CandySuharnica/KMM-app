package by.candy.suharnica.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.candy.suharnica.Greeting
import android.widget.TextView
import by.candy.suharnica.core.dataSource.network.CatalogLoader
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv: TextView = findViewById(R.id.text_view)
        MainScope().launch {
            kotlin.runCatching {
                CatalogLoader().getHtml()
            }.onSuccess {
                tv.text = it.toString()
            }.onFailure {
                tv.text = "Error: ${it.localizedMessage}"
            }
        }
    }
}
