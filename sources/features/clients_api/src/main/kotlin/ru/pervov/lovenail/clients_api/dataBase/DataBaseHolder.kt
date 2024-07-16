package ru.pervov.lovenail.clients_api.dataBase

import android.content.Context
import androidx.room.Room
import ru.pervov.lovenail.clients_api.dataBase.utils.DataBaseGetter
import ru.pervov.lovenail.clients_api.dataBase.utils.DataBaseSetting
import ru.pervov.lovenail.clients_api.dataBase.client.ClientDatabase
import ru.pervov.lovenail.clients_api.dataBase.client_rating.RatingDatabase
import ru.pervov.lovenail.clients_api.dataBase.comment.CommentDatabase

private const val CLIENT_DATABASE = "client-database"
private const val RATING_DATABASE = "rating-database"
private const val COMMENT_DATABASE = "comment-database"

internal class DataBaseHolder private constructor() : DataBaseSetting, DataBaseGetter {

    private var clientDatabase: ClientDatabase? = null
    private var ratingDatabase: RatingDatabase? = null
    private var commentDatabase: CommentDatabase? = null

    override fun init(context: Context) {
        clientDatabase = Room.databaseBuilder(
            context,
            ClientDatabase::class.java, CLIENT_DATABASE
        ).build()
        ratingDatabase = Room.databaseBuilder(
            context,
            RatingDatabase::class.java, RATING_DATABASE
        ).build()
        commentDatabase = Room.databaseBuilder(
            context,
            CommentDatabase::class.java, COMMENT_DATABASE
        ).build()
    }

    override fun getClientDataBase(): ClientDatabase {
        return clientDatabase ?: throw getIllegalStateException(CLIENT_DATABASE)
    }

    override fun getCommentDataBase(): CommentDatabase {
        return commentDatabase ?: throw getIllegalStateException(COMMENT_DATABASE)
    }

    override fun getRatingDataBase(): RatingDatabase {
        return ratingDatabase ?: throw getIllegalStateException(RATING_DATABASE)
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