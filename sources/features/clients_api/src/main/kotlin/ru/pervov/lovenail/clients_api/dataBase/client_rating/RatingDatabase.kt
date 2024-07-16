package ru.pervov.lovenail.clients_api.dataBase.client_rating

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pervov.lovenail.clients_api.dataBase.client.ClientDao
import ru.pervov.lovenail.clients_api.models.Rating

@Database(entities = [Rating::class], version = 1)
abstract class RatingDatabase : RoomDatabase() {
    abstract fun ratingDao(): RatingDao
}