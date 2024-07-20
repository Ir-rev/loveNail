package ru.pervov.client_create_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _action = MutableSharedFlow<ClientCreateAction>()
    val action: SharedFlow<ClientCreateAction> = _action.asSharedFlow()

    init {
        initCreateClientItemList()
    }

    private fun initCreateClientItemList() {
        val itemList = mutableListOf<CreateClientItem>()
        itemList.add(
            CreateClientItem.NameInput(
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
            CreateClientItem.WearTimeInput(
                id = UUID.randomUUID().toString(),
                sequenceNumber = itemList.size
            )
        )
        itemList.add(
            CreateClientItem.PriceInput(
                id = UUID.randomUUID().toString(),
                sequenceNumber = itemList.size
            )
        )
        viewModelScope.launch {
            _state.emit(itemList)
        }
    }

    fun createClient() {
        viewModelScope.launch(Dispatchers.IO) {
            val itemsList = _state.value
            if (itemsList.filterIsInstance<CreateClientItem.PhoneInput>()
                    .firstOrNull()?.phoneNumber?.length != 18
            ) {
                _action.emit(ClientCreateAction.ShowToast("Ввиди номер телефона"))
                return@launch
            }
            val name = itemsList.filterIsInstance<CreateClientItem.NameInput>().firstOrNull()?.name
            if (name.isNullOrBlank()) {
                _action.emit(ClientCreateAction.ShowToast("Ввиди имя"))
                return@launch
            }
        }
    }
}