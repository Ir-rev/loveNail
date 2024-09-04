package ru.pervov.procedure_api.repository

import kotlinx.coroutines.flow.SharedFlow
import ru.pervov.procedure_api.models.procedure.Procedure
import ru.pervov.procedure_api.models.procedure.ProcedureDataBaseAction

interface ProcedureRepository {

    val action: SharedFlow<ProcedureDataBaseAction>

    fun addProcedure(procedure: Procedure)

    fun removeProcedure(procedure: Procedure)

    fun updateProcedure(procedure: Procedure)

    fun getAllProcedures(): List<Procedure>

    fun getProcedureById(id: String): Procedure?

}