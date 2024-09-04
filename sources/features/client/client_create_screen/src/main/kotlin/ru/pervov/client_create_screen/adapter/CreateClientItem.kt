package ru.pervov.client_create_screen.adapter

sealed class CreateClientItem(
    val id: String
) {

    class PhoneInput(
        id: String,
        sequenceNumber: Int,
        var phoneNumber: String? = null
    ) : CreateClientItem(id)

    class NameInput(
        id: String,
        sequenceNumber: Int,
        var name: String? = null
    ) : CreateClientItem(id)

    class WearTimeInput(
        id: String,
        sequenceNumber: Int,
        var wearTime: Int? = null
    ) : CreateClientItem(id)

    class PriceInput(
        id: String,
        sequenceNumber: Int,
        var price: Int? = null
    ) : CreateClientItem(id)

}