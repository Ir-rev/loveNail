package ru.pervov.lovenail.procedure_create_screen.adapter

sealed class CreateProcedureItem(
    val id: String
) {

    class NameProcedureInput(
        id: String,
        sequenceNumber: Int,
        var name: String? = null
    ) : CreateProcedureItem(id)

    class DescriptionInput(
        id: String,
        sequenceNumber: Int,
        var description: String? = null
    ) : CreateProcedureItem(id)

    class PriceInput(
        id: String,
        sequenceNumber: Int,
        var price: Int? = null
    ) : CreateProcedureItem(id)

    class WorkTimeInput(
        id: String,
        sequenceNumber: Int,
        var workTime: Long? = null
    ) : CreateProcedureItem(id)

}