package ru.pervov.lovenail.week_calendar_screen.view_model

import ru.pervov.lovenail.calendar_api.models.Event


sealed class WeekCalendarViewModelState {

    class Loading() : WeekCalendarViewModelState()

    class Success(val eventList: List<Event>) : WeekCalendarViewModelState()

    class Error(val message: String) : WeekCalendarViewModelState()

}