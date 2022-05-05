package by.candy.suharnica.android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.candy.suharnica.CandySDK
import by.candy.suharnica.android.composeUI.MainScreen
import by.candy.suharnica.cache.DatabaseDriverFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : ComponentActivity() {

    private val sdk = CandySDK(DatabaseDriverFactory(this))
    private val factory = MainViewModel.Factory(sdk, ::createFile)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel =
            ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setContent {
            MainScreen(viewModel)
        }


        lifecycleScope.launch {
            viewModel.errorHandler.collectLatest { error ->
                if (error != "") Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun createFile(pickerInitialUri: Uri) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
            putExtra(Intent.EXTRA_TITLE, "Check.pdf")

            // Optionally, specify a URI for the directory that should be opened in
            // the system file picker before your app creates the document.
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
        }
        this.startActivityForResult(intent, CREATE_FILE)
        alterDocument(pickerInitialUri)
    }


    private fun alterDocument(uri: Uri) {
        try {
            contentResolver.openFileDescriptor(uri, "w")?.use {
                FileOutputStream(it.fileDescriptor).use {
                    it.write(
                        ("Hello Moto")
                            .toByteArray()
                    )
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val CREATE_FILE = 1
    }
}
