package ru.pervov.calendar_api.dataBase.utils

import ru.pervov.procedure_api.dataBase.procedure.ProcedureDatabase

internal interface DataBaseGetter {

    fun getProcedureDatabase(): ProcedureDatabase

}