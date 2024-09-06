package ru.pervov.lovenail.day_calendar_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pervov.lovenail.calendar_domain.CalendarDomainInstanceGetter

class DayCalendarViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DayCalendarViewModel(CalendarDomainInstanceGetter.getInstance().getCalendarEventUseCase()) as T
    }

}