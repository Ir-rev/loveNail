package ru.pervov.lovenail.date_utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class DateWorkHelper {

    fun getStartDayTime(localDate: LocalDate): Long {
        return localDate
            .atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toEpochSecond()
    }

    fun getTodayDayStartTime(): Long {
        return LocalDate
            .now()
            .atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toEpochSecond()
    }

    fun getEndDayTime(localDate: LocalDate): Long {
        return localDate
            .atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .plusDays(1)
            .toEpochSecond()
    }

    fun getLocalDateFromEpochSecond(dateAsLong: Long): LocalDate {
        return Instant.ofEpochSecond(dateAsLong).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    fun getLocalDateTimeFromEpochSecond(dateAsLong: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(dateAsLong), ZoneId.systemDefault())
    }

    fun getHoursAndMinutesFromEpochSecond(dateAsLong: Long): String {
        val localDateTime = getLocalDateTimeFromEpochSecond(dateAsLong)
        return "${formatMinutesOrHours(localDateTime.hour)}:${formatMinutesOrHours(localDateTime.minute)}"
    }

    private fun formatMinutesOrHours(time: Int): String {
        val timeAsString = time.toString()
        if (timeAsString.length < 2) return "0$timeAsString"
        return timeAsString
    }
}