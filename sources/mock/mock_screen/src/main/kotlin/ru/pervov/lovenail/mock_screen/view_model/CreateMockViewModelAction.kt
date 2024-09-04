package ru.pervov.lovenail.mock_screen.view_model

sealed class CreateMockViewModelAction {

    class ShowToast(val message: String) : CreateMockViewModelAction()

}