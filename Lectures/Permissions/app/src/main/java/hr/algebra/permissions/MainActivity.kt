package hr.algebra.permissions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import androidx.core.app.ActivityCompat
import hr.algebra.permissions.databinding.ActivityMainBinding

private const val PHONE_REQ = 1
private const val SMS_REQ = 2

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var canCall = binding.etNumber.text.isNotBlank()
                var canSend = canCall && binding.etMessage.text.isNotBlank()

                binding.btnPhone.isEnabled = canCall
                binding.btnPhoneDirect.isEnabled = canCall
                binding.btnSms.isEnabled = canSend
                binding.btnSmsDirect.isEnabled = canSend
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }

        binding.etNumber.addTextChangedListener(textWatcher)
        binding.etMessage.addTextChangedListener(textWatcher)
        binding.btnPhone.setOnClickListener { call() }
        binding.btnPhoneDirect.setOnClickListener { callDirect() }
        binding.btnSms.setOnClickListener { sms() }
        binding.btnSmsDirect.setOnClickListener { smsDirect() }
    }


    private fun call() = startActivity(
        Intent(
            Intent.ACTION_DIAL,
            Uri.parse("tel: ${binding.etNumber.text.toString().trim()}")
        )
    )

    private fun callDirect() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                PHONE_REQ
            )
            return
        }
        startActivity(
            Intent(
                Intent.ACTION_CALL,
                Uri.parse("tel: ${binding.etNumber.text.toString().trim()}")
            )
        )
    }

    private fun sms() = startActivity(
        Intent(
            Intent.ACTION_SENDTO,
            Uri.parse("smsto: ${binding.etNumber.text.toString().trim()}")
        ).apply {
            putExtra("sms_body", binding.etMessage.text.toString())
        }
    )

    private fun smsDirect() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.SEND_SMS),
                SMS_REQ
            )
            return
        }
        SmsManager.getDefault().sendTextMessage(
            binding.etNumber.text.toString().trim(),
            null,
            binding.etMessage.text.toString().trim(),
            null,
            null
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when {
            PHONE_REQ == requestCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED -> callDirect()
            SMS_REQ == requestCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED -> smsDirect()
        }
    }
}