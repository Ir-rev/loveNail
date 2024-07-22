package ru.pervov.procedure_api

import ru.pervov.procedure_api.repository.ProcedureRepository
import ru.pervov.procedure_api.repository.ProcedureRepositoryImpl

class ProcedureApiInstanceGetter private constructor() {

    fun getProcedureRepository(): ProcedureRepository {
        return ProcedureRepositoryImpl.getInstance()
    }

    companion object {

        private var INSTANCE: ProcedureApiInstanceGetter? = null

        fun getInstance(): ProcedureApiInstanceGetter {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: ProcedureApiInstanceGetter()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }

}