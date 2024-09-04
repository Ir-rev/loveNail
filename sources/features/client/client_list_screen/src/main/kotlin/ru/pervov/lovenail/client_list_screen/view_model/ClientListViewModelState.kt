package ru.pervov.lovenail.client_list_screen.view_model

import ru.pervov.lovenail.client_list_screen.adapter.ClientRecyclerItem

sealed class ClientListViewModelState {

    class Loading() : ClientListViewModelState()

    class Success(val clientList: List<ClientRecyclerItem>) : ClientListViewModelState()

    class Error(val message: String) : ClientListViewModelState()

}