package com.copper.debt.common

import java.text.SimpleDateFormat
import java.util.*

const val datePattern = "yyyy-MM-dd HH:mm:ss"
val ruLocale: Locale = Locale("ru")
val dataDateFormat = SimpleDateFormat(datePattern, Locale.ENGLISH)



fun Calendar.getFormatTime(): String {
    return dataDateFormat.format(this.time)
}