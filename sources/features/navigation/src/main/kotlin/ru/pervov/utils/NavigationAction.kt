package ru.pervov.utils

sealed class NavigationAction {

    class OpenClientList() : NavigationAction()

    class OpenCreateClient(val clientId: String?) : NavigationAction()

}