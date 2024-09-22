package ru.pervov.lovenail.day_calendar_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.pervov.lovenail.calendar_domain.api.CalendarEventUseCase
import ru.pervov.lovenail.day_calendar_screen.adapter.EventRecyclerItem
import java.time.LocalDate

class DayCalendarViewModel(
    private val calendarEventUseCase: CalendarEventUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<DayCalendarViewModelState> =
        MutableStateFlow(DayCalendarViewModelState.Loading())
    val state: StateFlow<DayCalendarViewModelState> = _state.asStateFlow()

    private var fetchEventListJob: Job? = null

    init {
        fetchEventList(LocalDate.now())
    }

    fun selectNextDay() {
        val state = state.value
        if (state !is DayCalendarViewModelState.Success) return
        fetchEventList(state.date.plusDays(1))
    }

    fun selectPreviousDay() {
        val state = state.value
        if (state !is DayCalendarViewModelState.Success) return
        fetchEventList(state.date.minusDays(1))
    }

    private fun fetchEventList(date: LocalDate) {
        fetchEventListJob?.cancel()
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
            viewModelScope.launch {
                _state.emit(
                    DayCalendarViewModelState.Error(
                        throwable.localizedMessage ?: "Error"
                    )
                )
            }
        }) {
            _state.emit(DayCalendarViewModelState.Loading())
            val allEventList = calendarEventUseCase.getEventForDay(date = date)
            val eventItemList = mutableListOf<EventRecyclerItem>()
            if (allEventList.isEmpty()) {
                eventItemList.add(EventRecyclerItem.EmptyListItem)
            } else {
                eventItemList.addAll(allEventList.map {
                    EventRecyclerItem.EventItem(it)
                })
            }
            _state.emit(DayCalendarViewModelState.Success(date = date, eventList = eventItemList))
        }
    }

}