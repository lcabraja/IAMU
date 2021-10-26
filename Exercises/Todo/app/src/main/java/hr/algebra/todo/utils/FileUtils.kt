package hr.algebra.todo.utils

import android.os.Environment

fun isExternalStorageWritable() =
    Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

fun isExternalStorageReadable() =
    isExternalStorageWritable() ||
            Environment.MEDIA_MOUNTED_READ_ONLY == Environment.getExternalStorageState()