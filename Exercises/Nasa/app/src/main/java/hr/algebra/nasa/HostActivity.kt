package hr.algebra.nasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.nasa.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            android.R.anim.fade_in, android.R.anim.fade_out
        )
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}