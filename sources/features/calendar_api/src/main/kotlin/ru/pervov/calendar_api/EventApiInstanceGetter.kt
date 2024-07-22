package ru.pervov.calendar_api

import ru.pervov.calendar_api.repository.EventRepository
import ru.pervov.calendar_api.repository.EventRepositoryImpl

class EventApiInstanceGetter private constructor() {

    fun getProcedureRepository(): EventRepository {
        return EventRepositoryImpl.getInstance()
    }

    companion object {

        private var INSTANCE: EventApiInstanceGetter? = null

        fun getInstance(): EventApiInstanceGetter {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: EventApiInstanceGetter()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }

}