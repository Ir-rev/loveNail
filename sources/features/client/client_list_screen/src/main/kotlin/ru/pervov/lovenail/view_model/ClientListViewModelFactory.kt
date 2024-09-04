package ru.pervov.lovenail.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pervov.lovenail.clients_api.ClientApiInstanceGetter
import ru.pervov.lovenail.clients_api.repository.ClientsRepository

class ClientListViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClientListViewModel(ClientApiInstanceGetter.getInstance().getClientsRepository()) as T
    }

}