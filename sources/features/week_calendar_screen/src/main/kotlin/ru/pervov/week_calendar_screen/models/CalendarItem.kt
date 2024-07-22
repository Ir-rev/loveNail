package ru.pervov.week_calendar_screen.models

import java.util.UUID

sealed class CalendarItem {

    class Event(
        val id: Long,
    ) : CalendarItem()

}