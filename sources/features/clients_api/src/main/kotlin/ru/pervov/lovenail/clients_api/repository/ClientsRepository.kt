package ru.pervov.lovenail.clients_api.repository

import kotlinx.coroutines.flow.SharedFlow
import ru.pervov.lovenail.clients_api.models.Client
import ru.pervov.lovenail.clients_api.models.ClientDataBaseAction

interface ClientsRepository {

    val action: SharedFlow<ClientDataBaseAction>

    fun addClient(client: Client)

    fun removeClient(client: Client)

    fun updateClient(client: Client)

    fun getAllClients(): List<Client>

    fun getClientById(id: String): Client?

}