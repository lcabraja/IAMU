package hr.algebra.nasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.nasa.databinding.ActivitySplashScreenBinding
import hr.algebra.nasa.framework.*

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.algebra.nasa.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimation()
        redirect()
    }

    private fun redirect() {
        // ako su podaci importani, redirect:
        if (getBooleanPreference(DATA_IMPORTED)) {
            callDelayed(DELAY) { startActivity<HostActivity>() }
        } else {
            if (isOnline()) {
                // 1. ako si online, pokreni servis za skidanje podataka
                Intent(this, NasaService::class.java).apply {
                    NasaService.enqueue(
                        this@SplashScreenActivity,
                        this
                    )
                }
            } else {
                // 2. u suprotnom ispisi poruku i izidji
                binding.tvSplash.text = getString(R.string.NO_INTERNET)
                callDelayed(DELAY) { finish() }
            }
        }
    }

    private fun startAnimation() {
        binding.ivSplash.startAnimation(R.anim.rotate)
        binding.tvSplash.startAnimation(R.anim.blink)
    }
}