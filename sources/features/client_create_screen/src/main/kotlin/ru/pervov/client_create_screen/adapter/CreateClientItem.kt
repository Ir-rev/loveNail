package ru.pervov.client_create_screen.adapter

sealed class CreateClientItem(
    val id: String,
    val sequenceNumber: Int
) {

    class PhoneInput(
        id: String,
        sequenceNumber: Int,
        phoneNumber: String? = null
    ) : CreateClientItem(id, sequenceNumber)

    class Name(
        id: String,
        sequenceNumber: Int,
        name: String? = null
    ) : CreateClientItem(id, sequenceNumber)

    class WearTime(
        id: String,
        sequenceNumber: Int,
        wearTime: Int? = null
    ) : CreateClientItem(id, sequenceNumber)

    class Price(
        id: String,
        sequenceNumber: Int,
        price: Int? = null
    ) : CreateClientItem(id, sequenceNumber)

}