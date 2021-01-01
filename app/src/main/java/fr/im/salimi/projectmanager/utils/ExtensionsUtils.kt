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

fun calculateProgress(startingDate: Long, currentDate: Long, endingDate: Long): Int {
    return if (endingDate == startingDate) {
        0
    } else {
        val value = (((currentDate - startingDate).toDouble() / (endingDate - startingDate).toDouble())* 100).toInt()
        if (value > 100)
            100
        else
            value
    }
}

fun String.sumASCIIChars(): Int {
    var sum = 0
    for (c in this)
        sum += c.toInt()
    return sum
}