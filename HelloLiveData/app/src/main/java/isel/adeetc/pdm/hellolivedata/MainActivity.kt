package isel.adeetc.pdm.hellolivedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import isel.adeetc.pdm.hellolivedata.kotlinx.getViewModel
import isel.adeetc.pdm.hellolivedata.kotlinx.observe
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = this.getViewModel<AViewModel>()
        model.data.observe(this) { msgTextView.text = it }

        hitMeButton.setOnClickListener { model.updateData() }
    }
}
