package ru.pervov.client_create_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pervov.lovenail.clients_api.ClientApiInstanceGetter

class ClientListViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClientCreateViewModel(
            ClientApiInstanceGetter
                .getInstance()
                .getClientsRepository()
        ) as T
    }

}