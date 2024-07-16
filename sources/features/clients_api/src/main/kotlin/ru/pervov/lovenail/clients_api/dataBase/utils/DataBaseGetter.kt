package ru.pervov.lovenail.clients_api.dataBase.utils

import ru.pervov.lovenail.clients_api.dataBase.client.ClientDatabase
import ru.pervov.lovenail.clients_api.dataBase.client_rating.RatingDatabase
import ru.pervov.lovenail.clients_api.dataBase.comment.CommentDatabase

internal interface DataBaseGetter {

    fun getClientDataBase(): ClientDatabase
    fun getCommentDataBase(): CommentDatabase
    fun getRatingDataBase(): RatingDatabase

}