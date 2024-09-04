package ru.pervov.procedure_list_screen.view_model

import ru.pervov.procedure_list_screen.adapter.ProcedureRecyclerItem

sealed class ProcedureListViewModelState {

    class Loading() : ProcedureListViewModelState()

    class Success(val procedureList: List<ProcedureRecyclerItem>) : ProcedureListViewModelState()

    class Error(val message: String) : ProcedureListViewModelState()

}