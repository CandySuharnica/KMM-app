package by.candy.suharnica.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import by.candy.suharnica.CandySDK
import by.candy.suharnica.android.composeUI.MainScreen
import by.candy.suharnica.cache.DatabaseDriverFactory


class MainActivity : ComponentActivity() {

    private val sdk = CandySDK(DatabaseDriverFactory(this))
    private val factory = MainViewModel.Factory(sdk)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
        setContent{
            MainScreen(viewModel)
        }
    }
}
