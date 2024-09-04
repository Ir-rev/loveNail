package ru.pervov.lovenail.clients_api.dataBase.client

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pervov.lovenail.clients_api.models.Client

@Database(entities = [Client::class], version = 1)
abstract class ClientDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
}