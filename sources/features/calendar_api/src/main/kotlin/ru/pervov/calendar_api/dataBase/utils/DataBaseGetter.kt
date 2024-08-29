package ru.pervov.calendar_api.dataBase.utils

import ru.pervov.calendar_api.dataBase.event.EventDatabase

internal interface DataBaseGetter {

    fun getCalendarDatabase(): EventDatabase

}