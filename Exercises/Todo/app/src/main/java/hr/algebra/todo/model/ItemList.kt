package hr.algebra.todo.model

import android.content.Context
import android.util.Log
import hr.algebra.todo.utils.isExternalStorageReadable
import hr.algebra.todo.utils.isExternalStorageWritable
import java.io.File

private const val DIR = "todo"
private const val FILE = "items.txt"

class ItemList(private val context: Context) : ArrayList<Item>() {

    fun saveInFile() {
        if (!isExternalStorageWritable()) {
            return
        }
        try {
            File(context.getExternalFilesDir(DIR), FILE)
                .bufferedWriter().use {
                    for(item in this) {
                        it.write(item.prepareForFileLine())
                    }
                }
        } catch (e: Exception) {
            Log.e(javaClass.name, e.toString(), e)
        }
    }
    fun readFromFile() {
        if (!isExternalStorageReadable()) {
            return
        }
        try {
            File(context.getExternalFilesDir(DIR), FILE)
                .useLines { lines ->
                    lines.forEach { line ->
                        add(Item.parseFromFileLine(line))
                    }
                }
        } catch (e: Exception) {
            Log.e(javaClass.name, e.toString(), e)
        }
    }
}