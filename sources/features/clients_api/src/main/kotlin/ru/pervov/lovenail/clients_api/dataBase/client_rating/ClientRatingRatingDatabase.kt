package ru.pervov.lovenail.clients_api.dataBase.client_rating

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pervov.lovenail.clients_api.models.ClientRating

@Database(entities = [ClientRating::class], version = 1)
abstract class ClientRatingRatingDatabase : RoomDatabase() {
    abstract fun ratingDao(): ClientRatingDao
}