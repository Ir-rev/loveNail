package ru.pervov.lovenail.calendar_api.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.pervov.lovenail.calendar_api.dataBase.DataBaseHolder
import ru.pervov.lovenail.calendar_api.models.Event
import ru.pervov.lovenail.calendar_api.models.EventDataBaseAction

internal class EventRepositoryImpl private constructor() : EventRepository {

    private val eventDao = DataBaseHolder.getInstance().getCalendarDatabase().eventDao()
    private val scope = CoroutineScope(SupervisorJob())
    private val _action = MutableSharedFlow<EventDataBaseAction>()
    override val action: SharedFlow<EventDataBaseAction> = _action.asSharedFlow()
    private var updateDataBaseJob: Job? = null

    override fun addEvent(event: Event) {
        eventDao.insertAll(event)
        updateDataBase()
    }

    override fun removeEvent(event: Event) {
        eventDao.delete(event)
        updateDataBase()
    }

    override fun updateEvent(event: Event) {
        eventDao.updateAll(event)
        updateDataBase()
    }

    override fun getAllEvents(): List<Event> {
        return eventDao.getAll()
    }

    override fun getEventById(id: String): Event? {
        return eventDao.loadAllByIds(id).firstOrNull()
    }

    private fun updateDataBase() {
        updateDataBaseJob?.cancel()
        updateDataBaseJob = scope.launch {
            _action.emit(EventDataBaseAction.UpdateDataBase())
        }
    }

    companion object {

        private var INSTANCE: EventRepositoryImpl? = null

        fun getInstance(): EventRepositoryImpl {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: EventRepositoryImpl()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }
}