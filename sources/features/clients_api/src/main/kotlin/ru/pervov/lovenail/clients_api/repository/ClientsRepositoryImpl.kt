package ru.pervov.lovenail.clients_api.repository

import ru.pervov.lovenail.clients_api.dataBase.DataBaseHolder
import ru.pervov.lovenail.clients_api.models.Client

internal class ClientsRepositoryImpl private constructor() : ClientsRepository {

    private val clientDao = DataBaseHolder.getInstance().getDataBase().clientDao()

    override fun addClient(client: Client) {
        clientDao.insertAll(client)
    }

    override fun removeClient(client: Client) {
        clientDao.delete(client)
    }

    override fun updateClient(client: Client) {
        clientDao.updateAll(client)
    }

    override fun getAllClient(): List<Client> {
        return clientDao.getAll()
    }

    override fun getClientById(id: String): Client? {
        return clientDao.loadAllByIds(id).firstOrNull()
    }

    companion object {

        private var INSTANCE: ClientsRepositoryImpl? = null

        fun getInstance(): ClientsRepositoryImpl {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: ClientsRepositoryImpl()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }
}