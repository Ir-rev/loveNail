package ru.pervov.week_calendar_screen.view_model


sealed class WeekCalendarViewModelState {

    class Loading() : WeekCalendarViewModelState()

    class Success(val eventList: List<Unit>) : WeekCalendarViewModelState()

    class Error(val message: String) : WeekCalendarViewModelState()

}