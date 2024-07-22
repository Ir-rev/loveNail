package ru.pervov.lovenail.clients_api.dataBase.utils

import ru.pervov.lovenail.clients_api.dataBase.client.ClientDatabase
import ru.pervov.lovenail.clients_api.dataBase.client_rating.ClientRatingRatingDatabase
import ru.pervov.lovenail.clients_api.dataBase.comment.ClientCommentDatabase

internal interface DataBaseGetter {

    fun getClientDataBase(): ClientDatabase
    fun getCommentDataBase(): ClientCommentDatabase
    fun getRatingDataBase(): ClientRatingRatingDatabase

}