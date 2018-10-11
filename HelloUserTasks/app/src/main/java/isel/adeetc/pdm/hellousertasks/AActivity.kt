package isel.adeetc.pdm.hellousertasks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_a.*

class AActivity : LoggingActivity("AActivity") {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        sendButton.setOnClickListener {
            if (messageEditText.text.isBlank()) {
                Toast.makeText(this, R.string.enter_text_toast, Toast.LENGTH_LONG).show()
            }
            else {
                navigateToBActivity(messageEditText.text.toString())
            }
        }
    }

    private fun navigateToBActivity(message: String) {
        val intent = Intent(this, BActivity::class.java)
        intent.putExtra(BParameterExtraKey, message)
        startActivity(intent)
    }
}
