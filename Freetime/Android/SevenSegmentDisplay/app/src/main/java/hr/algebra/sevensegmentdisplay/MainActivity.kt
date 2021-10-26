package hr.algebra.sevensegmentdisplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import hr.algebra.sevensegmentdisplay.databinding.ActivityMainBinding
import hr.algebra.sevensegmentdisplay.utils.toInt
import java.lang.Math.pow
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val booleans = MutableList(8) { false }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createButtons()
    }

    private fun createButtons() {
        for (x in 0..7) {
            val imageView = ImageView(this).apply {
                layoutParams = ViewGroup.LayoutParams(120, 120)
                setImageResource(R.drawable.zero)
                setOnClickListener {
                    if (booleans[x]) {
                        setImageResource(R.drawable.zero)
                    } else {
                        setImageResource(R.drawable.one)
                    }
                    booleans[x] = booleans[x].not()
                    tryDisplayingNumber()
                }
            }
            binding.buttonPanel.addView(imageView)
        }
    }

    private fun tryDisplayingNumber() {
        val binaryRepresentation = booleanToBinary()
        binding.tvDisplay.text =
            when (binaryRepresentation) {
                63 -> "0"
                191 -> "0."
                6 -> "1"
                134 -> "1."
                91 -> "2"
                219 -> "2."
                79 -> "3"
                207 -> "3."
                102 -> "4"
                230 -> "4."
                109 -> "5"
                237 -> "5."
                125 -> "6"
                253 -> "6."
                7 -> "7"
                135 -> "7."
                127 -> "8"
                255 -> "8."
                111 -> "9"
                239 -> "9."
                119 -> "A"
                247 -> "A."
                252 -> "b"
                124 -> "b."
                57 -> "C"
                185 -> "C."
                94 -> "d"
                222 -> "d."
                121 -> "E"
                249 -> "E."
                113 -> "F"
                241 -> "F."
                else -> "?"
            }
    }

    private fun booleanToBinary(): Int {
        var index = 0.0;
        val fold = booleans.fold(0) { sum, current -> (sum + current.toInt() * 2.0.pow(x = index++)).toInt() }
        println(fold)
        return fold
    }
}