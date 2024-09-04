package ru.pervov.lovenail.procedure_list_screen.adapter

import ru.pervov.lovenail.procedure_api.models.procedure.Procedure

sealed class ProcedureRecyclerItem {

    class EmptyListItem() : ProcedureRecyclerItem()
    class ProcedureItem(val procedure: Procedure) : ProcedureRecyclerItem()

}