package ru.pervov.lovenail.mock_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pervov.lovenail.calendar_api.CalendarApiInstanceGetter
import ru.pervov.lovenail.clients_api.ClientApiInstanceGetter
import ru.pervov.lovenail.procedure_api.ProcedureApiInstanceGetter

class CreateMockViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateMockViewModel(
            clientsRepository = ClientApiInstanceGetter.getInstance().getClientsRepository(),
            procedureRepository = ProcedureApiInstanceGetter.getInstance().getProcedureRepository(),
            eventRepository = CalendarApiInstanceGetter.getInstance().getEventRepository()
        ) as T
    }

}