package ru.pervov.lovenail.client_list_screen.adapter

import ru.pervov.lovenail.clients_api.models.Client

sealed class ClientRecyclerItem {

    class EmptyListItem() : ClientRecyclerItem()
    class ClientItem(val client: Client) : ClientRecyclerItem()

}