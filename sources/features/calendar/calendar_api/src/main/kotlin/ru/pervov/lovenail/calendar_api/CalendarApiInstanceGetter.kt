package ru.pervov.lovenail.calendar_api

import ru.pervov.lovenail.calendar_api.dataBase.DataBaseHolder
import ru.pervov.lovenail.calendar_api.dataBase.utils.DataBaseSetting
import ru.pervov.lovenail.calendar_api.repository.EventRepository
import ru.pervov.lovenail.calendar_api.repository.EventRepositoryImpl

class CalendarApiInstanceGetter private constructor() {

    fun getEventRepository(): EventRepository {
        return EventRepositoryImpl.getInstance()
    }

    fun getCalendarDatabaseSetting(): DataBaseSetting {
        return getDataBaseHolder()
    }

    private fun getDataBaseHolder(): DataBaseHolder {
        return DataBaseHolder.getInstance()
    }

    companion object {

        private var INSTANCE: CalendarApiInstanceGetter? = null

        fun getInstance(): CalendarApiInstanceGetter {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: CalendarApiInstanceGetter()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }

}