package ru.pervov.lovenail.calendar_domain.api

import ru.pervov.lovenail.calendar_domain.model.EventDomain

interface CalendarEventUseCase {

    /** возвращает список ивентов */
    suspend fun getEventList(): List<EventDomain>

}