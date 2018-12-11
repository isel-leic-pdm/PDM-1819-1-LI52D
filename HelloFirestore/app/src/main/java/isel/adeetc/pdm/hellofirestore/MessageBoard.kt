package isel.adeetc.pdm.hellofirestore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*


class MessageBoard(private val app: HelloFirestoreApplication) {

    val content = MutableLiveData<Map<String, String>>()
    val db = FirebaseFirestore.getInstance()

    init {
        val docRef = db
            .collection("messageBoard")
            .document("message")

        docRef.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, e ->
            if (e != null) {
                Log.w(app.TAG, "Listen failed.", e)
                return@EventListener
            }
            if (snapshot != null && snapshot.exists()) {
                content.value = snapshot.data.orEmpty().mapValues { it.value.toString() }
                Log.d(app.TAG, "data: ${snapshot.data}")
            }
        })
    }

    fun post(newMessage: Map<String, String>) {
        val docRef = db
            .collection("messageBoard")
            .document("message")

        docRef.set(newMessage)
    }
}