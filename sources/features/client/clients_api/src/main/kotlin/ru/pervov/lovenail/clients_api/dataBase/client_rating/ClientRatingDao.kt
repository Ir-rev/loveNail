package ru.pervov.lovenail.clients_api.dataBase.client_rating

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.pervov.lovenail.clients_api.models.ClientRating

@Dao
interface ClientRatingDao {

    @Query("SELECT * FROM ClientRating")
    fun getAll(): List<ClientRating>

    @Query("SELECT * FROM ClientRating WHERE id IN (:ratingId)")
    fun loadAllByIds(ratingId: String): List<ClientRating>

    @Insert
    fun insertAll(vararg rating: ClientRating)

    @Delete
    fun delete(rating: ClientRating)

    @Update
    fun updateAll(rating: ClientRating)
}