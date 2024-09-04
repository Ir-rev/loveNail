package ru.pervov.procedure_list_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pervov.procedure_api.ProcedureApiInstanceGetter

class ProcedureListViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProcedureListViewModel(ProcedureApiInstanceGetter.getInstance().getProcedureRepository()) as T
    }

}