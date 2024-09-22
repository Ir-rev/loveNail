package ru.pervov.lovenail.calendar_domain.api

import ru.pervov.lovenail.calendar_domain.model.EventDomain
import java.time.LocalDate

interface CalendarEventUseCase {

    /** возвращает список ивентов */
    suspend fun getEventForDay(date: LocalDate): List<EventDomain>

}