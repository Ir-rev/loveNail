package ru.pervov.lovenail.calendar_domain

import ru.pervov.lovenail.calendar_api.CalendarApiInstanceGetter
import ru.pervov.lovenail.calendar_domain.api.CalendarEventUseCase
import ru.pervov.lovenail.calendar_domain.usecases.CalendarEventUseCaseImpl
import ru.pervov.lovenail.clients_api.ClientApiInstanceGetter
import ru.pervov.lovenail.procedure_api.ProcedureApiInstanceGetter

class CalendarDomainInstanceGetter private constructor() {

    fun getCalendarEventUseCase(): CalendarEventUseCase {
        return CalendarEventUseCaseImpl.getInstance(
            eventRepository = CalendarApiInstanceGetter.getInstance().getEventRepository(),
            clientsRepository = ClientApiInstanceGetter.getInstance().getClientsRepository(),
            procedureRepository = ProcedureApiInstanceGetter.getInstance().getProcedureRepository()
        )
    }

    companion object {

        private var INSTANCE: CalendarDomainInstanceGetter? = null

        fun getInstance(): CalendarDomainInstanceGetter {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: CalendarDomainInstanceGetter()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }
}