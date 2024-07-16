package ru.pervov.lovenail.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.pervov.lovenail.adapter.ClientRecyclerItem
import ru.pervov.lovenail.clients_api.models.Client
import ru.pervov.lovenail.clients_api.repository.ClientsRepository
import java.util.UUID

class ClientListViewModel(
    private val clientsRepository: ClientsRepository
) : ViewModel() {

    private val _state: MutableStateFlow<ClientListViewModelState> =
        MutableStateFlow(ClientListViewModelState.Loading())
    val state: StateFlow<ClientListViewModelState> = _state.asStateFlow()

    private var fetchClientJob: Job? = null

    init {
        fetchClientList()
        viewModelScope.launch {
            clientsRepository.action.collect {
                fetchClientList()
            }
        }
    }

    fun fetchClientList() {
        fetchClientJob?.cancel()
        fetchClientJob =
            viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
                viewModelScope.launch {
                    _state.emit(
                        ClientListViewModelState.Error(
                            throwable.localizedMessage ?: "Error"
                        )
                    )
                }
            }) {
                _state.emit(ClientListViewModelState.Loading())
                val clientList = clientsRepository.getAllClient()
                val clientRecyclerItem: List<ClientRecyclerItem> = if (clientList.isEmpty()) {
                    listOf(ClientRecyclerItem.EmptyListItem())
                } else {
                    clientList.map { ClientRecyclerItem.ClientItem(it) }
                }
                _state.emit(ClientListViewModelState.Success(clientRecyclerItem))
            }
    }

    override fun onCleared() {
        fetchClientJob?.cancel()
        fetchClientJob = null
        super.onCleared()
    }
}