package ru.pervov.lovenail.clients_api.dataBase.comment

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.pervov.lovenail.clients_api.models.ClientComment

@Dao
interface ClientCommentDao {

    @Query("SELECT * FROM clientComment")
    fun getAll(): List<ClientComment>

    @Query("SELECT * FROM clientComment WHERE id IN (:commentId)")
    fun loadAllByIds(commentId: String): List<ClientComment>

    @Insert
    fun insertAll(vararg comment: ClientComment)

    @Delete
    fun delete(comment: ClientComment)

    @Update
    fun updateAll(comment: ClientComment)
}