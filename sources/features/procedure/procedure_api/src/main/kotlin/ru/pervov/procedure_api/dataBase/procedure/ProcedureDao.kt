package ru.pervov.procedure_api.dataBase.procedure

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.pervov.procedure_api.models.procedure.Procedure

@Dao
interface ProcedureDao {

    @Query("SELECT * FROM Procedure")
    fun getAll(): List<Procedure>

    @Query("SELECT * FROM Procedure WHERE id IN (:procedureId)")
    fun loadAllByIds(procedureId: String): List<Procedure>

    @Insert
    fun insertAll(vararg procedure: Procedure)

    @Delete
    fun delete(procedure: Procedure)

    @Update
    fun updateAll(procedure: Procedure)

}