package isel.adeetc.pdm.hellolivedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this).get(AViewModel::class.java)
        model.data.observe(this, Observer<String> { msgTextView.text = it })

        hitMeButton.setOnClickListener { model.updateData() }
    }
}
