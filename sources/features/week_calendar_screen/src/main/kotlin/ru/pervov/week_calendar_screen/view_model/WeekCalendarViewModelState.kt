package ru.pervov.week_calendar_screen.view_model

import ru.pervov.calendar_api.models.Event


sealed class WeekCalendarViewModelState {

    class Loading() : WeekCalendarViewModelState()

    class Success(val eventList: List<Event>) : WeekCalendarViewModelState()

    class Error(val message: String) : WeekCalendarViewModelState()

}