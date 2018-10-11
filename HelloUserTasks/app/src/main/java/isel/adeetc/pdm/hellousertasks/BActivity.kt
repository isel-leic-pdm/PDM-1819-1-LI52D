package isel.adeetc.pdm.hellousertasks

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_b.*

const val BParameterExtraKey = "BActivity.extra"

class BActivity : LoggingActivity("BActivity") {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        messageView.text = intent.getStringExtra(BParameterExtraKey)

        okButton.setOnClickListener {
            finish()
        }
    }
}
