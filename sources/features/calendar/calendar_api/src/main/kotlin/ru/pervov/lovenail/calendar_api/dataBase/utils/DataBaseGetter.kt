package ru.pervov.lovenail.calendar_api.dataBase.utils

import ru.pervov.lovenail.calendar_api.dataBase.event.EventDatabase

internal interface DataBaseGetter {

    fun getCalendarDatabase(): EventDatabase

}