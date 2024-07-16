package ru.pervov.lovenail.view_model

import ru.pervov.lovenail.adapter.ClientRecyclerItem

sealed class ClientListViewModelState {

    class Loading() : ClientListViewModelState()

    class Success(val clientList: List<ClientRecyclerItem>) : ClientListViewModelState()

    class Error(val message: String) : ClientListViewModelState()

}