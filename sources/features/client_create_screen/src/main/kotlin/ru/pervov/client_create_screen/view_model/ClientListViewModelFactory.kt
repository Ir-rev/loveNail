package ru.pervov.client_create_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pervov.lovenail.clients_api.ClientApiInstanceGetter

class ClientListViewModelFactory(
    private val clientId: String?
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClientCreateViewModel(
            clientsRepository = ClientApiInstanceGetter
                .getInstance()
                .getClientsRepository(),
            clientId = clientId
        ) as T
    }

}