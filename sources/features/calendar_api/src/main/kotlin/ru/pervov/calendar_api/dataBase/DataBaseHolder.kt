package ru.pervov.calendar_api.dataBase

import android.content.Context
import androidx.room.Room
import ru.pervov.calendar_api.dataBase.event.EventDatabase
import ru.pervov.calendar_api.dataBase.utils.DataBaseGetter
import ru.pervov.calendar_api.dataBase.utils.DataBaseSetting

private const val EVENT_DATABASE = "event-database"

internal class DataBaseHolder private constructor() : DataBaseSetting, DataBaseGetter {

    private var eventDatabase: EventDatabase? = null

    override fun init(context: Context) {
        eventDatabase = Room.databaseBuilder(
            context,
            EventDatabase::class.java, EVENT_DATABASE
        ).build()
    }

    override fun getEventDatabase(): EventDatabase {
        return eventDatabase ?: throw getIllegalStateException(EVENT_DATABASE)
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