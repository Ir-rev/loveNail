package ru.pervov.lovenail.calendar_domain.model

import ru.pervov.lovenail.calendar_api.models.Event
import ru.pervov.lovenail.clients_api.models.Client
import ru.pervov.lovenail.procedure_api.models.procedure.Procedure

data class EventDomain(
    val event: Event,
    val client: Client,
    val procedure: Procedure
)