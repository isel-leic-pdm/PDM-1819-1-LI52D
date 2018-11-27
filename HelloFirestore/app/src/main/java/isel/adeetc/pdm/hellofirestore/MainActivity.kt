package isel.adeetc.pdm.hellofirestore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

const val ID_KEY = "ID"
const val MESSAGE_KEY = "Message"

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    private fun updateView(message: Map<String, String>) {
        messageView.text = "${message[ID_KEY]?.subSequence(0..2)} : ${message[MESSAGE_KEY]}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as HelloFirestoreApplication

        val messageBoard = (application as HelloFirestoreApplication).messageBoard
        updateView(messageBoard.content.value.orEmpty())

        messageBoard.content.observe(this, Observer {
            updateView(it)
        })

        postButton.setOnClickListener {
            val message = messageToPost.text.toString()
            if (message.isNotBlank()) {
                messageBoard.post(mapOf(ID_KEY to app.deviceId.toString(), MESSAGE_KEY to message))
            }
        }
    }
}
