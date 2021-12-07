package hr.algebra.nasa

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.nasa.framework.startActivity

class NasaReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity<HostActivity>()
    }
}