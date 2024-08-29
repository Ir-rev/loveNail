package ru.pervov.week_calendar_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pervov.calendar_api.CalendarApiInstanceGetter

class WeekCalendarViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeekCalendarViewModel(CalendarApiInstanceGetter.getInstance().getEventRepository()) as T
    }

}