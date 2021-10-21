package hr.algebra.questionaire.utils

import android.content.Context
import android.widget.Toast


fun Context.showToast(text: String, duration: Int) =
    Toast.makeText(this, text, duration).show()