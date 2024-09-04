package ru.pervov.lovenail.mock_screen.view_model

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.pervov.lovenail.calendar_api.models.Event
import ru.pervov.lovenail.calendar_api.repository.EventRepository
import ru.pervov.lovenail.clients_api.models.Client
import ru.pervov.lovenail.clients_api.repository.ClientsRepository
import ru.pervov.lovenail.procedure_api.models.procedure.Procedure
import ru.pervov.lovenail.procedure_api.repository.ProcedureRepository
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.TemporalAccessor
import java.time.temporal.TemporalField
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import java.util.UUID

class CreateMockViewModel(
    private val clientsRepository: ClientsRepository,
    private val procedureRepository: ProcedureRepository,
    private val eventRepository: EventRepository,
) : ViewModel() {

    val action: MutableSharedFlow<CreateMockViewModelAction> = MutableSharedFlow()

    private var addMockJob: Job? = null
    private var clearMockJob: Job? = null

    fun clearMock() {
        clearMockJob?.cancel()
        clearMockJob = viewModelScope.launch(Dispatchers.IO) {
            clientsRepository.getAllClients().forEach {
                clientsRepository.removeClient(it)
            }
            procedureRepository.getAllProcedures().forEach {
                procedureRepository.removeProcedure(it)
            }
            eventRepository.getAllEvents().forEach {
                eventRepository.removeEvent(it)
            }
            action.emit(CreateMockViewModelAction.ShowToast("Очищенно"))
        }
    }

    fun addMock() {
        addMockJob?.cancel()
        addMockJob = viewModelScope.launch(Dispatchers.IO) {
            clientsRepository.addClient(
                Client(
                    id = UUID.randomUUID().toString(),
                    name = "Александр Первов",
                    phoneNumber = "+79963813976",
                    price = 2500,
                    wearTimeInDays = 21
                )
            )
            procedureRepository.addProcedure(
                Procedure(
                    id = UUID.randomUUID().toString(),
                    name = "Покрытие",
                    description = "description description description description description",
                    price = 2500,
                    workTimeInMinutes = 120
                )
            )
            val timeNow = getTodayDate()
            eventRepository.addEvent(
                Event(
                    id = UUID.randomUUID().toString(),
                    dateStart = getTimePlusHours(timeNow,5),
                    dateEnd = getTimePlusHours(timeNow,8),
                    description = "description description description description description",
                    clientId = clientsRepository.getAllClients().first().id,
                    procedureId = procedureRepository.getAllProcedures().first().id,
                )
            )
            action.emit(CreateMockViewModelAction.ShowToast("Создано"))
        }
    }

    private fun getTimePlusHours(time: Long, hours: Long): Long {
        val oldTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),TimeZone.getDefault().toZoneId())
        return oldTime.plusHours(hours).atZone(ZoneId.systemDefault()).toEpochSecond()
    }

    private fun getTodayDate(): Long {
        return LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond()
    }


    override fun onCleared() {
        addMockJob?.cancel()
        clearMockJob?.cancel()
        addMockJob = null
        clearMockJob = null
        super.onCleared()
    }
}