package isel.adeetc.pdm.hellofirestore

import android.app.Application
import com.google.firebase.FirebaseApp
import java.util.*

class HelloFirestoreApplication : Application() {

    val TAG = "HelloFirestore"
    val deviceId = UUID.randomUUID()

    lateinit var messageBoard: MessageBoard

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        messageBoard = MessageBoard(this)
    }
}