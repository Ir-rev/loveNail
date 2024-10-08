package ru.pervov.lovenail.procedure_api

import ru.pervov.lovenail.procedure_api.dataBase.utils.DataBaseSetting
import ru.pervov.lovenail.procedure_api.dataBase.DataBaseHolder
import ru.pervov.lovenail.procedure_api.repository.ProcedureRepository
import ru.pervov.lovenail.procedure_api.repository.ProcedureRepositoryImpl

class ProcedureApiInstanceGetter private constructor() {

    fun getProcedureRepository(): ProcedureRepository {
        return ProcedureRepositoryImpl.getInstance()
    }

    fun getProcedureDatabaseSetting(): DataBaseSetting {
        return getDataBaseHolder()
    }

    private fun getDataBaseHolder(): DataBaseHolder {
        return DataBaseHolder.getInstance()
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