package ru.pervov.lovenail.procedure_create_screen.view_model

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
import ru.pervov.lovenail.procedure_api.models.procedure.Procedure
import ru.pervov.lovenail.procedure_api.repository.ProcedureRepository
import ru.pervov.lovenail.procedure_create_screen.adapter.CreateProcedureItem
import java.util.UUID

class ProcedureCreateViewModel(
    private val procedureRepository: ProcedureRepository,
    private val procedureId: String?
) : ViewModel() {

    private val _state: MutableStateFlow<List<CreateProcedureItem>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<CreateProcedureItem>> = _state.asStateFlow()

    private val _action = MutableSharedFlow<ProcedureCreateAction>()
    val action: SharedFlow<ProcedureCreateAction> = _action.asSharedFlow()

    init {
        initCreateClientItemList()
    }

    private fun initCreateClientItemList() {
        viewModelScope.launch(Dispatchers.IO) {
            val procedure = procedureId?.let { procedureRepository.getProcedureById(it) }
            val itemList = mutableListOf<CreateProcedureItem>()
            itemList.add(
                CreateProcedureItem.NameProcedureInput(
                    id = UUID.randomUUID().toString(),
                    sequenceNumber = itemList.size,
                    name = procedure?.name
                )
            )
            itemList.add(
                CreateProcedureItem.DescriptionInput(
                    id = UUID.randomUUID().toString(),
                    sequenceNumber = itemList.size,
                    description = procedure?.description
                )
            )
            itemList.add(
                CreateProcedureItem.PriceInput(
                    id = UUID.randomUUID().toString(),
                    sequenceNumber = itemList.size,
                    price = procedure?.price
                )
            )
            itemList.add(
                CreateProcedureItem.WorkTimeInput(
                    id = UUID.randomUUID().toString(),
                    sequenceNumber = itemList.size,
                    workTime = procedure?.workTime
                )
            )
            _state.emit(itemList)
        }
    }

    fun createClient() {
        viewModelScope.launch(Dispatchers.IO) {
            val itemsList = _state.value
            if (validateFields(itemsList).not()) return@launch

            val name = itemsList
                .filterIsInstance<CreateProcedureItem.NameProcedureInput>()
                .firstOrNull()
                ?.name

            val description = itemsList
                .filterIsInstance<CreateProcedureItem.DescriptionInput>()
                .firstOrNull()
                ?.description

            val price = itemsList
                .filterIsInstance<CreateProcedureItem.PriceInput>()
                .firstOrNull()
                ?.price

            val workTime = itemsList
                .filterIsInstance<CreateProcedureItem.WorkTimeInput>()
                .firstOrNull()
                ?.workTime

            val procedure = Procedure(
                id = procedureId ?: UUID.randomUUID().toString(),
                name = name ?: "",
                description = description ?: "",
                price = price ?: 0,
                workTime = workTime ?: 0
            )
            if (procedureId == null) {
                procedureRepository.addProcedure(procedure)
            } else {
                procedureRepository.updateProcedure(procedure)
            }
            _action.emit(ProcedureCreateAction.ProcedureCreatedOrUpdated())
        }
    }

    private suspend fun validateFields(itemsList: List<CreateProcedureItem>): Boolean {
        val name = itemsList
            .filterIsInstance<CreateProcedureItem.NameProcedureInput>()
            .firstOrNull()
            ?.name
        if (name.isNullOrBlank()) {
            _action.emit(ProcedureCreateAction.ShowToast("Ввиди имя"))
            return false
        }
        return true
    }
}