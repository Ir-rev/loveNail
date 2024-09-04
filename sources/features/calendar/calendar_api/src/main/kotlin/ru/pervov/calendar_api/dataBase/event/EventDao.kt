package ru.pervov.calendar_api.dataBase.event

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.pervov.calendar_api.models.Event

@Dao
interface EventDao {

    @Query("SELECT * FROM Event")
    fun getAll(): List<Event>

    @Query("SELECT * FROM Event WHERE id IN (:eventId)")
    fun loadAllByIds(eventId: String): List<Event>

    @Insert
    fun insertAll(vararg events: Event)

    @Delete
    fun delete(event: Event)

    @Update
    fun updateAll(event: Event)

}