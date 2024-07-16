package ru.pervov.lovenail.clients_api.dataBase.client_rating

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.pervov.lovenail.clients_api.models.Rating

@Dao
interface RatingDao {

    @Query("SELECT * FROM rating")
    fun getAll(): List<Rating>

    @Query("SELECT * FROM rating WHERE id IN (:ratingId)")
    fun loadAllByIds(ratingId: String): List<Rating>

    @Insert
    fun insertAll(vararg rating: Rating)

    @Delete
    fun delete(rating: Rating)

    @Update
    fun updateAll(rating: Rating)
}