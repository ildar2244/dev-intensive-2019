package ru.skillbranch.devintensive.exstensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/** Created by qq_3000 on 08.10.2019. */

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val MONTH = 30 * DAY
const val YEAR = 365 * DAY

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    MONTH,
    YEAR
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        TimeUnits.MONTH -> value * MONTH
        TimeUnits.YEAR -> value * YEAR
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = Date().time - date.time
    val calcSecond = TimeUnit.MILLISECONDS.toSeconds(diff)
    return when(calcSecond) {
        in 0..1 -> "только что"
        in 1..45 -> "несколько секунд назад"
        in 45..75 -> "минуту назад"
        in 75..2700 -> toNamingNumber((calcSecond / 60).toInt(), "minute")
        in 2700..4500 -> "час назад"
        in 4500..31680 -> toNamingNumber((calcSecond / (60*60)).toInt(), "hour")
        in 31680..37440 -> "день назад"
        in 37440..31104000 -> toNamingNumber((calcSecond / (60*60*24)).toInt(), "day")
        in 31104000..Long.MAX_VALUE -> "более года назад"
        else -> "nothing"
    }
}

fun toNamingNumber(number: Int, type: String?): String {
    return when(type) {
        "minute" -> {
            when(number) {
                2,3,4 -> "$number минуты назад"
                else -> "$number минут назад"
            }
        }
        "hour" -> {
            when(number) {
                2,3,4 -> "$number часа назад"
                else -> "$number часов назад"
            }
        }
        "day" -> {
            when(number) {
                2,3,4 -> "$number дня назад"
                else -> "$number дней назад"
            }
        }
        else -> "$number"
    }
}