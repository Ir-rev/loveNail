package ru.pervov.procedure_api.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.pervov.procedure_api.dataBase.DataBaseHolder
import ru.pervov.procedure_api.models.procedure.Procedure
import ru.pervov.procedure_api.models.procedure.ProcedureDataBaseAction

internal class ProcedureRepositoryImpl private constructor() : ProcedureRepository {

    private val procedureDao = DataBaseHolder.getInstance().getProcedureDatabase().procedureDao()
    private val scope = CoroutineScope(SupervisorJob())
    private val _action = MutableSharedFlow<ProcedureDataBaseAction>()
    override val action: SharedFlow<ProcedureDataBaseAction> = _action.asSharedFlow()
    private var updateDataBaseJob: Job? = null

    override fun addProcedure(procedure: Procedure) {
        procedureDao.insertAll(procedure)
        updateDataBase()
    }

    override fun removeProcedure(procedure: Procedure) {
        procedureDao.delete(procedure)
        updateDataBase()
    }

    override fun updateProcedure(procedure: Procedure) {
        procedureDao.updateAll(procedure)
        updateDataBase()
    }

    override fun getAllProcedures(): List<Procedure> {
        return procedureDao.getAll()
    }

    override fun getProcedureById(id: String): Procedure? {
        return procedureDao.loadAllByIds(id).firstOrNull()
    }

    private fun updateDataBase() {
        updateDataBaseJob?.cancel()
        updateDataBaseJob = scope.launch {
            _action.emit(ProcedureDataBaseAction.UpdateDataBase())
        }
    }

    companion object {

        private var INSTANCE: ProcedureRepositoryImpl? = null

        fun getInstance(): ProcedureRepositoryImpl {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: ProcedureRepositoryImpl()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }
}