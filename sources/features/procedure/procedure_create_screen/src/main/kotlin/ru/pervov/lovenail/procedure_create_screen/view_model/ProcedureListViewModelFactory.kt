package ru.pervov.lovenail.procedure_create_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pervov.lovenail.procedure_api.ProcedureApiInstanceGetter

class ProcedureListViewModelFactory(
    private val procedureId: String?
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProcedureCreateViewModel(
            procedureRepository = ProcedureApiInstanceGetter.getInstance().getProcedureRepository(),
            procedureId = procedureId
        ) as T
    }

}