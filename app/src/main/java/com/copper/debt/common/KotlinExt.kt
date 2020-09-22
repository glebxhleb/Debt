package com.copper.debt.common

import java.text.SimpleDateFormat
import java.util.*

const val datePattern = "yyyy-MM-dd HH:mm:ss"
val ruLocale: Locale = Locale("ru")
val dataDateFormat = SimpleDateFormat(datePattern, Locale.ENGLISH)


fun Calendar.getFormatTime(): String {
    return dataDateFormat.format(this.time)
}

fun String.getCalendar(): Calendar {

    val calendar = Calendar.getInstance()
    dataDateFormat.parse(this)?.let {
        calendar.time = it
    }
    return calendar

}

fun Double.format(): String {
    return when {
        this < 0.001 -> {
            ""
        }
        this % 1 < 0.001 -> {
            "%.0f".format(this)
        } else -> {
            "%.2f".format(this)
        }
    }
}

