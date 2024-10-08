package ru.pervov.lovenail.calendar_api.repository

import kotlinx.coroutines.flow.SharedFlow
import ru.pervov.lovenail.calendar_api.models.Event
import ru.pervov.lovenail.calendar_api.models.EventDataBaseAction

interface EventRepository {

    val action: SharedFlow<EventDataBaseAction>

    fun addEvent(event: Event)

    fun removeEvent(event: Event)

    fun updateEvent(event: Event)

    fun getAllEvents(): List<Event>

    fun getEventById(id: String): Event?

    fun getEventsByDate(startDate: Long, endDate: Long): List<Event>

}