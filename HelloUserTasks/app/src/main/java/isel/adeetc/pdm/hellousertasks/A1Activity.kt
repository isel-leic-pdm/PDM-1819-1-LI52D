package isel.adeetc.pdm.hellousertasks

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_a1.*

const val B1RequestCode = 1001

private class A1ViewModel(var content: String = "") : ViewModel()

class A1Activity : LoggingActivity("A1Activity") {

    private fun getViewModel(): A1ViewModel {
        return ViewModelProviders.of(this).get(A1ViewModel::class.java)
    }

    private fun updateUI(data: Intent?) {
        getViewModel().content = data?.getStringExtra(B1ActivityResultExtraKey).orEmpty()
        userInputView.text = getViewModel().content
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a1)

        userInputView.text = getViewModel().content
        goGetButton.setOnClickListener {
            startActivityForResult(Intent(this, B1Activity::class.java), B1RequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == B1RequestCode) {
            when (resultCode) {
                Activity.RESULT_OK -> updateUI(data)
                Activity.RESULT_CANCELED -> Toast.makeText(this, R.string.cancelled_toast, Toast.LENGTH_LONG).show()
                else -> throw IllegalStateException()
            }
        }
    }
}
