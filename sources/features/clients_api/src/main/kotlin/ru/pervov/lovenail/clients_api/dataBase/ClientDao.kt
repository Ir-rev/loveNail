package ru.pervov.lovenail.clients_api.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.pervov.lovenail.clients_api.models.Client

@Dao
interface ClientDao {

    @Query("SELECT * FROM client")
    fun getAll(): List<Client>

    @Query("SELECT * FROM client WHERE id IN (:clientsId)")
    fun loadAllByIds(clientsId: String): List<Client>

    @Insert
    fun insertAll(vararg clients: Client)

    @Delete
    fun delete(client: Client)

    @Update
    fun updateAll(client: Client)
}