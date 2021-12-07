package hr.algebra.nasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import hr.algebra.nasa.databinding.ActivitySplashScreenBinding
import hr.algebra.nasa.framework.getBooleanPreference
import hr.algebra.nasa.framework.startActivity
import hr.algebra.nasa.framework.startAnimation

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
            Handler(Looper.getMainLooper()).postDelayed(
                { startActivity<HostActivity>() },
                DELAY
            )
        }
        // u suprotnom,
        // 1. ako si online, pokreni servis za skidanje podataka
        // 2. u suprotnom ispisi poruku i izidji
    }

    private fun startAnimation() {
        binding.ivSplash.startAnimation(R.anim.rotate)
        binding.tvSplash.startAnimation(R.anim.blink)
    }
}