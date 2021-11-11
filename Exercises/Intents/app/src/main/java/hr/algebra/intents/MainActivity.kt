package hr.algebra.intents

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import hr.algebra.intents.databinding.ActivityMainBinding

private const val MIME_TYPE = "text/plain"
private const val KEY = 1
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggleWidgets()
        setupListeners()
    }

    private fun toggleWidgets() {
        binding.ivSendSms.isEnabled =
            binding.etNumber.text.toString().isNotBlank()
                    && binding.etMessage.text.toString().isNotBlank()

        binding.ivShare.isEnabled = binding.etMessage.text.toString().isNotBlank()
    }

    private fun setupListeners() {

        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                toggleWidgets()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        }
        binding.etNumber.addTextChangedListener(textWatcher)
        binding.etMessage.addTextChangedListener(textWatcher)

        binding.ivPickContact.setOnClickListener {
            pickContact()
        }
        binding.ivSendSms.setOnClickListener {
            sendSms()
        }
        binding.ivShare.setOnClickListener {
            share()
        }
    }

    @SuppressLint("Range")
    private val pickContact = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val contactUri = it.data?.data!! // garantiram da mi podaci moraju stici
            val cursor = contentResolver.query(contactUri, null, null, null, null)
            if (cursor?.moveToFirst() == true) {
                val number = cursor.getString(cursor.getColumnIndex(NUMBER))
                binding.etNumber.setText(number)
            }
        }
    }
    private fun pickContact() {
        // intent koji picka contact
        with(Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)) {
            pickContact.launch(this) // registracija callbacka
        }
    }

    private fun sendSms() {
        if (permissionGranted()) {
            SmsManager.getDefault().sendTextMessage(
                binding.etNumber.text.toString(),
                null,
                binding.etMessage.text.toString(),
                null,
                null
            )
            Toast.makeText(this, getString(R.string.smsSent), Toast.LENGTH_SHORT).show()
        }
    }

    private fun permissionGranted(): Boolean =
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
            == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), KEY)
            false
        } else {
            true
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == KEY &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSms()
        }
    }

    private fun share() {
        Intent(Intent.ACTION_SEND).apply {
            type = MIME_TYPE
            putExtra(Intent.EXTRA_TEXT, binding.etMessage.text.toString())
        }.also {
            val chooser = Intent.createChooser(it, getString(R.string.shareMessage))
            startActivity(chooser)
        }
    }
}