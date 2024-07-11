package ru.pervov.lovenail.clients_api.dataBase

import android.content.Context
import androidx.room.Room

internal class DataBaseHolder private constructor() : ClientDataBaseSetting, ClientDataBaseGetter {

    private var db: ClientDatabase? = null

    override fun init(context: Context) {
        db = Room.databaseBuilder(
            context,
            ClientDatabase::class.java, "client-database"
        ).build()
    }

    override fun getDataBase(): ClientDatabase {
        return db ?: throw IllegalStateException("База данных ClientDatabase не иницилизирована")
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