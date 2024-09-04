package ru.pervov.lovenail.procedure_list_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.pervov.lovenail.procedure_list_screen.adapter.ProcedureRecyclerItem
import ru.pervov.lovenail.procedure_api.repository.ProcedureRepository

class ProcedureListViewModel(
    private val procedureRepository: ProcedureRepository
) : ViewModel() {

    private val _state: MutableStateFlow<ProcedureListViewModelState> =
        MutableStateFlow(ProcedureListViewModelState.Loading())
    val state: StateFlow<ProcedureListViewModelState> = _state.asStateFlow()

    private var fetchProcedureJob: Job? = null

    init {
        fetchProcedureList()
        viewModelScope.launch {
            procedureRepository.action.collect {
                fetchProcedureList()
            }
        }
    }

    fun fetchProcedureList() {
        fetchProcedureJob?.cancel()
        fetchProcedureJob =
            viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
                viewModelScope.launch {
                    _state.emit(
                        ProcedureListViewModelState.Error(
                            throwable.localizedMessage ?: "Error"
                        )
                    )
                }
            }) {
                _state.emit(ProcedureListViewModelState.Loading())
                val procedureList = procedureRepository.getAllProcedures()
                val procedureRecyclerItem: List<ProcedureRecyclerItem> = if (procedureList.isEmpty()) {
                    listOf(ProcedureRecyclerItem.EmptyListItem())
                } else {
                    procedureList.map { ProcedureRecyclerItem.ProcedureItem(it) }
                }
                _state.emit(ProcedureListViewModelState.Success(procedureRecyclerItem))
            }
    }

    override fun onCleared() {
        fetchProcedureJob?.cancel()
        fetchProcedureJob = null
        super.onCleared()
    }
}