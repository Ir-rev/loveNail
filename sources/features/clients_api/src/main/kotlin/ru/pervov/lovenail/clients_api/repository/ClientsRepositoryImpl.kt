package ru.pervov.lovenail.clients_api.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.pervov.lovenail.clients_api.dataBase.DataBaseHolder
import ru.pervov.lovenail.clients_api.models.Client
import ru.pervov.lovenail.clients_api.models.ClientDataBaseAction

internal class ClientsRepositoryImpl private constructor() : ClientsRepository {

    private val clientDao = DataBaseHolder.getInstance().getClientDataBase().clientDao()
    private val scope = CoroutineScope(SupervisorJob())
    private val _action = MutableSharedFlow<ClientDataBaseAction>()
    override val action: SharedFlow<ClientDataBaseAction> = _action.asSharedFlow()
    private var updateDataBaseJob: Job? = null

    override fun addClient(client: Client) {
        clientDao.insertAll(client)
        updateDataBase()
    }

    override fun removeClient(client: Client) {
        clientDao.delete(client)
        updateDataBase()
    }

    override fun updateClient(client: Client) {
        clientDao.updateAll(client)
        updateDataBase()
    }

    override fun getAllClients(): List<Client> {
        return clientDao.getAll()
    }

    override fun getClientById(id: String): Client? {
        return clientDao.loadAllByIds(id).firstOrNull()
    }

    private fun updateDataBase() {
        updateDataBaseJob?.cancel()
        updateDataBaseJob = scope.launch {
            _action.emit(ClientDataBaseAction.UpdateDataBase())
        }
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