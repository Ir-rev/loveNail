package ru.pervov.lovenail.clients_api.dataBase.comment

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.pervov.lovenail.clients_api.models.Comment

@Dao
interface CommentDao {

    @Query("SELECT * FROM comment")
    fun getAll(): List<Comment>

    @Query("SELECT * FROM comment WHERE id IN (:commentId)")
    fun loadAllByIds(commentId: String): List<Comment>

    @Insert
    fun insertAll(vararg comment: Comment)

    @Delete
    fun delete(comment: Comment)

    @Update
    fun updateAll(comment: Comment)
}