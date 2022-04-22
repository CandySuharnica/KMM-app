package by.candy.suharnica.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import by.candy.suharnica.CandySDK
import by.candy.suharnica.android.composeUI.MainScreen
import by.candy.suharnica.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val sdk = CandySDK(DatabaseDriverFactory(this))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainScreen()
        }
        /*MainScope().launch {
            kotlin.runCatching {
                sdk.getCatalogList()
            }.onSuccess {
                tv.text = it.toString()
            }.onFailure {
                tv.text = "Error: ${it.localizedMessage}"
            }
        }*/
    }
}
