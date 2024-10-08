package ru.pervov.lovenail.calendar_domain.usecases

import android.text.format.DateUtils
import ru.pervov.lovenail.calendar_api.repository.EventRepository
import ru.pervov.lovenail.calendar_domain.api.CalendarEventUseCase
import ru.pervov.lovenail.calendar_domain.model.EventDomain
import ru.pervov.lovenail.clients_api.repository.ClientsRepository
import ru.pervov.lovenail.date_utils.DateWorkHelper
import ru.pervov.lovenail.procedure_api.repository.ProcedureRepository
import java.time.LocalDate

private const val TAG = "[GetCalendarEventUseCaseImpl]"
private const val NOT_FOUND = "не найден"
private const val CLIENT = "клиент"
private const val PROCEDURE = "процедура"

internal class CalendarEventUseCaseImpl private constructor(
    private val eventRepository: EventRepository,
    private val clientsRepository: ClientsRepository,
    private val procedureRepository: ProcedureRepository
) : CalendarEventUseCase {

    private val dateUtils = DateWorkHelper()

    override suspend fun getEventForDay(date: LocalDate): List<EventDomain> {
        val eventList = eventRepository.getEventsByDate(
            dateUtils.getStartDayTime(date),
            dateUtils.getEndDayTime(date)
        ).sortedBy { it.dateStart }
        return eventList.map { event ->
            EventDomain(
                event = event,
                client = clientsRepository.getClientById(event.clientId)
                    ?: throwIllegalStateException(CLIENT, event.clientId),
                procedure = procedureRepository.getProcedureById(event.procedureId)
                    ?: throwIllegalStateException(PROCEDURE, event.procedureId),
            )
        }
    }

    private fun throwIllegalStateException(name: String, id: String): Nothing {
        throw IllegalStateException("$TAG $name c id = $id $NOT_FOUND")
    }

    companion object {

        private var INSTANCE: CalendarEventUseCaseImpl? = null

        fun getInstance(
            eventRepository: EventRepository,
            clientsRepository: ClientsRepository,
            procedureRepository: ProcedureRepository
        ): CalendarEventUseCaseImpl {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: CalendarEventUseCaseImpl(
                    eventRepository = eventRepository,
                    clientsRepository = clientsRepository,
                    procedureRepository = procedureRepository
                )
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }
}