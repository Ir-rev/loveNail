package ru.pervov.procedure_create_screen.view_model

sealed class ProcedureCreateAction {

    class ShowToast(val message: String) : ProcedureCreateAction()

    class ProcedureCreatedOrUpdated() : ProcedureCreateAction()

}