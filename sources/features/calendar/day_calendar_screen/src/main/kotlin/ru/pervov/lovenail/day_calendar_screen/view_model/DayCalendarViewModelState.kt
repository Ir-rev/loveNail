package ru.pervov.lovenail.day_calendar_screen.view_model

import ru.pervov.lovenail.day_calendar_screen.adapter.EventRecyclerItem
import java.time.LocalDate
import java.util.Date

sealed class DayCalendarViewModelState {

    class Loading() : DayCalendarViewModelState()

    class Success(val date: LocalDate, val eventList: List<EventRecyclerItem>) : DayCalendarViewModelState()

    class Error(val message: String) : DayCalendarViewModelState()

}