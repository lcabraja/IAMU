package hr.algebra.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import hr.algebra.intents.databinding.ActivityBrowserBinding
import hr.algebra.intents.databinding.ActivityMainBinding

class BrowserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBrowserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleUri()
    }

    private fun fixWebView() {
        binding.wvWebView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                    return true
                }
                return false
            }
        }
    }

    private fun handleUri() {
        intent.dataString?.let {
            binding.wvWebView.loadUrl(it)
        }
    }
}