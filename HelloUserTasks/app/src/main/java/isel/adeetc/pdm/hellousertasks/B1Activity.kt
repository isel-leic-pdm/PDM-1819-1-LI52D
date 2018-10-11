package isel.adeetc.pdm.hellousertasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_b1.*

const val B1ActivityResultExtraKey = "B1Activity.resultExtraKey"

class B1Activity : LoggingActivity("B1Activity") {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b1)

        okButton.setOnClickListener {
            setResult(
                    Activity.RESULT_OK,
                    Intent().putExtra(B1ActivityResultExtraKey, userInputEditText.text.toString())
            )
            finish()
        }

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
