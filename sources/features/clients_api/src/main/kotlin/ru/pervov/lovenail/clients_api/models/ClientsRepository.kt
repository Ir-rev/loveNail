package ru.pervov.lovenail.clients_api.models

interface ClientsRepository {

    fun addClient(client: Client)

    fun removeClients(): Unit

    fun changeClient(client: Client)

}