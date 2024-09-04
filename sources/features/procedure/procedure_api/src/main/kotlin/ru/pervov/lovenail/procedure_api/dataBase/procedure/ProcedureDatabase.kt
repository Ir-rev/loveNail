package ru.pervov.lovenail.procedure_api.dataBase.procedure

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pervov.lovenail.procedure_api.models.procedure.Procedure

@Database(entities = [Procedure::class], version = 1)
abstract class ProcedureDatabase : RoomDatabase() {
    abstract fun procedureDao(): ProcedureDao
}