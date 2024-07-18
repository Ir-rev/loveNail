package ru.pervov.client_create_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.pervov.client_create_screen.adapter.CreateClientItem
import ru.pervov.lovenail.clients_api.repository.ClientsRepository
import java.util.UUID

class ClientCreateViewModel(
    private val clientsRepository: ClientsRepository
) : ViewModel() {

    private val _state: MutableStateFlow<List<CreateClientItem>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<CreateClientItem>> = _state.asStateFlow()

    init {
        initCreateClientItemList()
    }

    private fun initCreateClientItemList() {
        val itemList = mutableListOf<CreateClientItem>()
        itemList.add(
            CreateClientItem.Name(
                id = UUID.randomUUID().toString(),
                sequenceNumber = itemList.size
            )
        )
        itemList.add(
            CreateClientItem.PhoneInput(
                id = UUID.randomUUID().toString(),
                sequenceNumber = itemList.size
            )
        )
        itemList.add(
            CreateClientItem.WearTime(
                id = UUID.randomUUID().toString(),
                sequenceNumber = itemList.size
            )
        )
        itemList.add(
            CreateClientItem.Price(
                id = UUID.randomUUID().toString(),
                sequenceNumber = itemList.size
            )
        )
        viewModelScope.launch {
            _state.emit(itemList)
        }
    }

}