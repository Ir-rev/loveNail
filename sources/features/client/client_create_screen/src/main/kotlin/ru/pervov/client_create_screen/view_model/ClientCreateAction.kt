package ru.pervov.client_create_screen.view_model

sealed class ClientCreateAction {

    class ShowToast(val message: String) : ClientCreateAction()

    class ClientCreatedOrUpdated() : ClientCreateAction()

}