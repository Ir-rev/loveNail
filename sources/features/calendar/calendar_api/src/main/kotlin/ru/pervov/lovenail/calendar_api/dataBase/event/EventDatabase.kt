package ru.pervov.lovenail.calendar_api.dataBase.event

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pervov.lovenail.calendar_api.models.Event

@Database(entities = [Event::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}