package hr.algebra.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.todo.databinding.ActivityMainBinding
import hr.algebra.todo.model.Item
import hr.algebra.todo.model.ItemList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemList: ItemList
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setupListeners()
    }

    private fun setAdapter() {
        itemList = ItemList(this)
        itemAdapter = ItemAdapter(itemList)
        binding.rwItems.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
        }
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            addItem()
        }
        binding.etItem.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text?.endsWith("\n") == true) {
                    addItem()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun addItem() {
        if (binding.etItem.text.toString().isNotBlank()) {
            itemList.add(
                Item(binding.etItem.text.toString().trim(), false)
            )
            itemAdapter.notifyItemInserted(itemList.size - 1)
            binding.etItem.text.clear()
        }
    }

    override fun onPause() {
        super.onPause()
        itemList.saveInFile()
    }

    override fun onResume() {
        super.onResume()
        if (itemList.isEmpty())
            itemList.readFromFile()
    }
}
