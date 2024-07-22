package ru.pervov.lovenail.clients_api.dataBase.comment

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pervov.lovenail.clients_api.models.ClientComment

@Database(entities = [ClientComment::class], version = 1)
abstract class ClientCommentDatabase : RoomDatabase() {
    abstract fun commentDao(): ClientCommentDao
}