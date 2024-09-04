package ru.pervov.calendar_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "clientId") val clientId: String,
    @ColumnInfo(name = "procedureId") val procedureId: String,
)