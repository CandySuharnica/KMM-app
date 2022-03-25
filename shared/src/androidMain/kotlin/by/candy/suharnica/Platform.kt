package by.candy.suharnica

import dev.gitlive.firebase.database.FirebaseDatabase

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"

}