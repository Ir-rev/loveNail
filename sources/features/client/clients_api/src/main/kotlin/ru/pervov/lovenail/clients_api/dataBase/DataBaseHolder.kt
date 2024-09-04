package ru.pervov.lovenail.clients_api.dataBase

import android.content.Context
import androidx.room.Room
import ru.pervov.lovenail.clients_api.dataBase.utils.DataBaseGetter
import ru.pervov.lovenail.clients_api.dataBase.utils.DataBaseSetting
import ru.pervov.lovenail.clients_api.dataBase.client.ClientDatabase
import ru.pervov.lovenail.clients_api.dataBase.client_rating.ClientRatingRatingDatabase
import ru.pervov.lovenail.clients_api.dataBase.comment.ClientCommentDatabase

private const val CLIENT_DATABASE = "client-database"
private const val RATING_DATABASE = "rating-database"
private const val COMMENT_DATABASE = "comment-database"

internal class DataBaseHolder private constructor() : DataBaseSetting, DataBaseGetter {

    private var clientDatabase: ClientDatabase? = null
    private var ratingDatabase: ClientRatingRatingDatabase? = null
    private var commentDatabase: ClientCommentDatabase? = null

    override fun init(context: Context) {
        clientDatabase = Room.databaseBuilder(
            context,
            ClientDatabase::class.java, CLIENT_DATABASE
        ).build()
        ratingDatabase = Room.databaseBuilder(
            context,
            ClientRatingRatingDatabase::class.java, RATING_DATABASE
        ).build()
        commentDatabase = Room.databaseBuilder(
            context,
            ClientCommentDatabase::class.java, COMMENT_DATABASE
        ).build()
    }

    override fun getClientDataBase(): ClientDatabase {
        return clientDatabase ?: throw getIllegalStateException(CLIENT_DATABASE)
    }

    override fun getCommentDataBase(): ClientCommentDatabase {
        return commentDatabase ?: throw getIllegalStateException(COMMENT_DATABASE)
    }

    override fun getRatingDataBase(): ClientRatingRatingDatabase {
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