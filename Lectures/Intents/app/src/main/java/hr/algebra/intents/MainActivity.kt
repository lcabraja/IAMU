package hr.algebra.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
                startActivity(this)
            }
        }
    }
}