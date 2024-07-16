package ru.pervov.lovenail.clients_api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Client(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
)

@Entity
data class Comment(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "clientId") val clientId: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "message") val message: String,
)

@Entity
data class Rating(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "clientId") val clientId: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "score") val score: Int,
)