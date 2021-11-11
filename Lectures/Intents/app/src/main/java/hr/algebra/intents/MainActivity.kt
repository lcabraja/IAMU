package hr.algebra.intents

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import hr.algebra.intents.databinding.ActivityMainBinding

const val OUTPUT_PARAM = "hr.algebra.intents.output.param"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val startActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (RESULT_OK == it.resultCode) {
            var nrVowels = it.data?.getIntExtra(OUTPUT_PARAM, -1)
            Toast.makeText(this, nrVowels.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnCount.setOnClickListener {
            with(Intent(this, CountVowelsActivity::class.java)) {
                putExtra(INPUT_PARAM, binding.etInput.text.toString().trim())
                startActivityForResult.launch(this)
            }
        }
    }
}