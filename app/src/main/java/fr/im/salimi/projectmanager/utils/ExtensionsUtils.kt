package fr.im.salimi.projectmanager.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toDate(format: String): Date? {
    val dateFormatter = SimpleDateFormat(format)
    return try {
        dateFormatter.parse(this)
    } catch (e: ParseException) {
        null
    }
}

@SuppressLint("SimpleDateFormat")
fun Date.toString(format: String): String {
    val dateFormatter = SimpleDateFormat(format)
    return dateFormatter.format(this)
}
