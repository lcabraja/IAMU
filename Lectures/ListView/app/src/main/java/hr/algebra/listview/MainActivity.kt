package hr.algebra.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import hr.algebra.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
    }

    private fun setAdapter() {
        val adapter = ShowsAdapter(
            this,
            listOf(
                Show("Monty Python", R.drawable.monty_python),
                Show("Black Adder", R.drawable.black_adder),
                Show("Friends", R.drawable.friends)
            )
        )

        binding.lvShows.adapter = adapter
    }
}