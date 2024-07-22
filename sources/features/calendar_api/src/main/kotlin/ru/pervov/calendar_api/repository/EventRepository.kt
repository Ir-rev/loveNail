package ru.pervov.calendar_api.repository

import kotlinx.coroutines.flow.SharedFlow
import ru.pervov.calendar_api.models.Event
import ru.pervov.calendar_api.models.EventDataBaseAction

interface EventRepository {

    val action: SharedFlow<EventDataBaseAction>

    fun addEvent(event: Event)

    fun removeEvent(event: Event)

    fun updateEvent(event: Event)

    fun getAllEvents(): List<Event>

    fun getEventById(id: String): Event?

}