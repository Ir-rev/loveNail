package ru.pervov.week_calendar_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeekCalendarViewModel(
//    private val eventRepository: EventRepository
): ViewModel() {

    private val _state: MutableStateFlow<WeekCalendarViewModelState> =
        MutableStateFlow(WeekCalendarViewModelState.Loading())
    val state: StateFlow<WeekCalendarViewModelState> = _state.asStateFlow()

    private var fetchEventListJob: Job? = null

    init {
        fetchEventList()
        viewModelScope.launch {
//            eventRepository.action.collect {
//                fetchEventList()
//            }
        }
    }

    fun fetchEventList() {
        fetchEventListJob?.cancel()
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
            viewModelScope.launch {
                _state.emit(
                    WeekCalendarViewModelState.Error(
                        throwable.localizedMessage ?: "Error"
                    )
                )
            }
        }) {
            _state.emit(WeekCalendarViewModelState.Loading())
//            _state.emit(WeekCalendarViewModelState.Success(eventRepository.getAllEvents()))
        }
    }

}