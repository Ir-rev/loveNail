package ru.pervov.lovenail.clients_api.dataBase.comment

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pervov.lovenail.clients_api.dataBase.client.ClientDao
import ru.pervov.lovenail.clients_api.models.Comment
import ru.pervov.lovenail.clients_api.models.Rating

@Database(entities = [Comment::class], version = 1)
abstract class CommentDatabase : RoomDatabase() {
    abstract fun commentDao(): CommentDao
}