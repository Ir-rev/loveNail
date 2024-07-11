package ru.pervov.lovenail.clients_api.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.pervov.lovenail.clients_api.models.Client
import ru.pervov.lovenail.clients_api.models.CommentConvector

@Database(entities = [Client::class], version = 1)
@TypeConverters(CommentConvector::class)
abstract class ClientDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
}