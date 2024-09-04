package ru.pervov.lovenail.clients_api

import ru.pervov.lovenail.clients_api.dataBase.utils.DataBaseSetting
import ru.pervov.lovenail.clients_api.dataBase.DataBaseHolder
import ru.pervov.lovenail.clients_api.repository.ClientsRepository
import ru.pervov.lovenail.clients_api.repository.ClientsRepositoryImpl

class ClientApiInstanceGetter private constructor() {

    fun getClientsRepository(): ClientsRepository {
        return ClientsRepositoryImpl.getInstance()
    }

    fun getClientDataBaseSetting(): DataBaseSetting {
        return getDataBaseHolder()
    }

    private fun getDataBaseHolder(): DataBaseHolder {
        return DataBaseHolder.getInstance()
    }

    companion object {

        private var INSTANCE: ClientApiInstanceGetter? = null

        fun getInstance(): ClientApiInstanceGetter {
            return synchronized(this) {
                val currentInstance = INSTANCE ?: ClientApiInstanceGetter()
                INSTANCE = currentInstance
                currentInstance
            }
        }
    }

}