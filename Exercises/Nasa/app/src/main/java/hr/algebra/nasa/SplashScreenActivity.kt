package hr.algebra.nasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import hr.algebra.nasa.databinding.ActivitySplashScreenBinding
import hr.algebra.nasa.framework.startActivity
import hr.algebra.nasa.framework.startAnimation


const val DELAY = 3000L

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
        Handler(Looper.getMainLooper()).postDelayed(
            { startActivity<HostActivity>() },
            DELAY
        )
    }

    private fun startAnimation() {
        binding.ivSplash.startAnimation(R.anim.rotate)
        binding.tvSplash.startAnimation(R.anim.blink)
    }
}