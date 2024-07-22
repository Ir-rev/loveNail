package ru.pervov.utils

sealed class NavigationAction {

    // client
    class OpenClientList() : NavigationAction()
    class OpenCreateOrUpdateClient(val clientId: String?) : NavigationAction()

    // calendar
    class OpenWeekCalendar() : NavigationAction()

    // procedure
    class OpenCreateOrUpdateProcedure(val procedureId: String?) : NavigationAction()


}