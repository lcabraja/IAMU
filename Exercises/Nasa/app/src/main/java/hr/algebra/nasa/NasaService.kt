package hr.algebra.nasa

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.nasa.framework.sendBroadcast
import hr.algebra.nasa.framework.setBooleanPreference

private const val JOB_ID = 1

class NasaService : JobIntentService() {
    // bg method
    override fun onHandleWork(intent: Intent) {
        // fake work
        Thread.sleep(6000)
        // ovo na kraju postavljam
        // setBooleanPreference(DATA_IMPORTED, true)
        sendBroadcast<NasaReceiver>()
    }

    companion object {
        fun enqueue(context: Context, intent: Intent) {
            enqueueWork(context, NasaService::class.java, JOB_ID, intent)
        }
    }
}