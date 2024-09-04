package ru.pervov.procedure_api.dataBase

import android.content.Context
import androidx.room.Room
import ru.pervov.procedure_api.dataBase.utils.DataBaseGetter
import ru.pervov.procedure_api.dataBase.utils.DataBaseSetting
import ru.pervov.procedure_api.dataBase.procedure.ProcedureDatabase

private const val PROCEDURE_DATABASE = "procedure-database"

internal class DataBaseHolder private constructor() : DataBaseSetting, DataBaseGetter {

    private var procedureDatabase: ProcedureDatabase? = null

    override fun init(context: Context) {
        procedureDatabase = Room.databaseBuilder(
            context,
            ProcedureDatabase::class.java,
            PROCEDURE_DATABASE
        ).build()
    }

    override fun getProcedureDatabase(): ProcedureDatabase {
        return procedureDatabase ?: throw getIllegalStateException(PROCEDURE_DATABASE)
    }

    private fun getIllegalStateException(dataBaseName: String): IllegalStateException {
        return IllegalStateException("База данных $dataBaseName не иницилизирована")
    }

    companion object {

        private var INSTANCE: DataBaseHolder? = null

        fun getInstance(): DataBaseHolder {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: DataBaseHolder()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }
}