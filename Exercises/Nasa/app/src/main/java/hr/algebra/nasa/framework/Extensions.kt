package hr.algebra.nasa.framework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import hr.algebra.nasa.HostActivity

fun View.startAnimation(animationId: Int) =
    startAnimation(AnimationUtils.loadAnimation(context, animationId))

inline fun <reified T : Activity> Context.startActivity() =
    startActivity(Intent(this, T::class.java).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })