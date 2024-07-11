package ru.pervov.lovenail.clients_api.repository

import ru.pervov.lovenail.clients_api.models.Client

interface ClientsRepository {

    fun addClient(client: Client)

    fun removeClient(client: Client)

    fun updateClient(client: Client)

    fun getAllClient(): List<Client>

    fun getClientById(id: String): Client?

}