package ru.pervov.lovenail.client_create_screen.view_model

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
import ru.pervov.lovenail.client_create_screen.adapter.CreateClientItem
import ru.pervov.lovenail.clients_api.models.Client
import ru.pervov.lovenail.clients_api.repository.ClientsRepository
import java.util.UUID

class ClientCreateViewModel(
    private val clientsRepository: ClientsRepository,
    private val clientId: String?
) : ViewModel() {

    private val _state: MutableStateFlow<List<CreateClientItem>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<CreateClientItem>> = _state.asStateFlow()

    private val _action = MutableSharedFlow<ClientCreateAction>()
    val action: SharedFlow<ClientCreateAction> = _action.asSharedFlow()

    init {
        initCreateClientItemList()
    }

    private fun initCreateClientItemList() {
        viewModelScope.launch(Dispatchers.IO) {
            val client = clientId?.let { clientsRepository.getClientById(it) }
            val itemList = mutableListOf<CreateClientItem>()
            itemList.add(
                CreateClientItem.NameInput(
                    id = UUID.randomUUID().toString(),
                    sequenceNumber = itemList.size,
                    name = client?.name
                )
            )
            itemList.add(
                CreateClientItem.PhoneInput(
                    id = UUID.randomUUID().toString(),
                    sequenceNumber = itemList.size,
                    phoneNumber = client?.phoneNumber
                )
            )
            itemList.add(
                CreateClientItem.WearTimeInput(
                    id = UUID.randomUUID().toString(),
                    sequenceNumber = itemList.size,
                    wearTime = client?.wearTime
                )
            )
            itemList.add(
                CreateClientItem.PriceInput(
                    id = UUID.randomUUID().toString(),
                    sequenceNumber = itemList.size,
                    price = client?.price
                )
            )
            _state.emit(itemList)
        }
    }

    fun createClient() {
        viewModelScope.launch(Dispatchers.IO) {
            val itemsList = _state.value
            if (validateFields(itemsList).not()) return@launch

            val name = itemsList.filterIsInstance<CreateClientItem.NameInput>().firstOrNull()?.name
            val phone =
                itemsList.filterIsInstance<CreateClientItem.PhoneInput>().firstOrNull()?.phoneNumber
            val price =
                itemsList.filterIsInstance<CreateClientItem.PriceInput>().firstOrNull()?.price
            val wearTime =
                itemsList.filterIsInstance<CreateClientItem.WearTimeInput>().firstOrNull()?.wearTime

            val client = Client(
                id = clientId ?: UUID.randomUUID().toString(),
                name = name ?: "",
                phoneNumber = phone ?: "",
                price = price ?: 0,
                wearTime = wearTime ?: 0
            )
            if (clientId == null) {
                clientsRepository.addClient(client)
            } else {
                clientsRepository.updateClient(client)
            }
            _action.emit(ClientCreateAction.ClientCreatedOrUpdated())
        }
    }

    private suspend fun validateFields(itemsList: List<CreateClientItem>): Boolean {
        if (itemsList.filterIsInstance<CreateClientItem.PhoneInput>()
                .firstOrNull()?.phoneNumber?.length != 18
        ) {
            _action.emit(ClientCreateAction.ShowToast("Ввиди номер телефона"))
            return false
        }
        val name = itemsList.filterIsInstance<CreateClientItem.NameInput>().firstOrNull()?.name
        if (name.isNullOrBlank()) {
            _action.emit(ClientCreateAction.ShowToast("Ввиди имя"))
            return false
        }
        return true
    }
}