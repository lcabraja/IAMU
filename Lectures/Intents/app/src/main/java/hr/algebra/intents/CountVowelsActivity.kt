package hr.algebra.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.*

const val INPUT_PARAM = "hr.algebra.intents.input.param"
class CountVowelsActivity : AppCompatActivity() {

    private var nrVowels = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_vowels)

        handleIntent()
        Toast.makeText(this, "Calculated, go back", Toast.LENGTH_SHORT).show()
    }

    private fun handleIntent() {
        val value = intent.getStringExtra(INPUT_PARAM)?.let {
            countVowels(it)
        }
    }

    private fun countVowels(value: String) {
        nrVowels = value.lowercase(Locale.getDefault()).replace("[^aeiou]".toRegex(), "").length
    }

    override fun finish() {
        setResult(RESULT_OK, Intent().apply {
            putExtra(OUTPUT_PARAM, nrVowels)
        })
        super.finish()
    }
}