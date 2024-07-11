package ru.pervov.lovenail.clients_api.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

private const val SPACER = "&#185"

@Entity
data class Client(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
    @ColumnInfo(name = "comment")
    val comment: List<Comment>
) {
    @Parcelize
    data class Comment(
        val id: String,
        val date: String,
        val message: String,
        val score: Int
    ) : Parcelable
}

class CommentConvector() {

    @TypeConverter
    fun fromComment(comments: List<Client.Comment>): String {
        return comments.map {
            Gson().toJson(it)
        }.reduce { first, second -> first + SPACER + second }
    }

    @TypeConverter
    fun toComment(input: String): List<Client.Comment> {
        return input.split(SPACER).map {
            Gson().fromJson(input, Client.Comment::class.java)
        }
    }

}