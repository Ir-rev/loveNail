package ru.pervov.lovenail.procedure_api.dataBase.utils

import ru.pervov.lovenail.procedure_api.dataBase.procedure.ProcedureDatabase

internal interface DataBaseGetter {

    fun getProcedureDatabase(): ProcedureDatabase

}