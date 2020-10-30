package com.copper.debt.common

import com.copper.debt.App
import com.copper.debt.R
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

const val datePattern = "dd.MM.yyyy HH:mm"
const val datePatternMonth = "dd MMMM HH:mm"
const val timePattern = "HH:mm"

val ruLocale: Locale = Locale("ru")
val dataDateFormat = SimpleDateFormat(datePattern, Locale.ENGLISH)


fun Calendar.getFormatTime(): String = dataDateFormat.format(this.time)

fun Long.getDate(): String {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_YEAR)

    calendar.timeInMillis = this

    if (currentYear != calendar.get(Calendar.YEAR)) {
        return SimpleDateFormat(datePattern, ruLocale).format(Date(this))
    } else if (currentDay != calendar.get(Calendar.DAY_OF_YEAR)) {
        return SimpleDateFormat(datePatternMonth, ruLocale).format(Date(this))
    } else {
        return App.instance.getString(R.string.today) + " " + SimpleDateFormat(
            timePattern,
            ruLocale
        ).format(Date(this))
    }
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
            "%.0f".format(Locale.ROOT, this)
        }
        else -> {
            "%.2f".format(Locale.ROOT, this)
        }
    }
}

