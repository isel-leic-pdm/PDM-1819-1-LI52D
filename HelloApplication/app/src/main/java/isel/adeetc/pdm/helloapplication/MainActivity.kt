package isel.adeetc.pdm.helloapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

const val greeting_key = "greeting"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val savedGreeting = savedInstanceState?.getString(greeting_key)
        if (savedGreeting != null)
            greetingView.text =  savedGreeting

        greetButton.setOnClickListener {
            greetingView.text = resources.getString(R.string.greeting, nameInput.text)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(greeting_key, greetingView.text.toString())
    }
}
