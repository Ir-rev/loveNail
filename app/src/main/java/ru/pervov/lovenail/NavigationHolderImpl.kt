package ru.pervov.lovenail

import android.app.Activity
import android.os.Bundle
import androidx.navigation.Navigation
import ru.pervov.lovenail.client_create_screen.fragment.CLIENT_ID
import ru.pervov.lovenail.procedure_create_screen.fragment.PROCEDURE_ID
import ru.pervov.lovenail.utils.NavigationAction
import ru.pervov.lovenail.utils.NavigationHolder

class NavigationHolderImpl(
    activity: Activity
) : NavigationHolder {

    private val navigation by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    override fun doNavigation(navigationAction: NavigationAction) {
        when (navigationAction) {

            // client
            is NavigationAction.OpenClientList -> {
                navigation.navigate(R.id.action_global_clientListFragment)
            }
            is NavigationAction.OpenCreateOrUpdateClient -> {
                navigation.navigate(R.id.action_global_clientCreateFragment, args = Bundle().apply {
                    putString(CLIENT_ID, navigationAction.clientId)
                })
            }

            // calendar
            is NavigationAction.OpenWeekCalendar -> {
                navigation.navigate(R.id.action_global_weekCalendarFragment)
            }

            // procedure
            is NavigationAction.OpenProcedureList -> {
                navigation.navigate(R.id.action_global_procedureListFragment)
            }
            is NavigationAction.OpenCreateOrUpdateProcedure -> {
                navigation.navigate(R.id.action_global_procedureCreateFragment,args = Bundle().apply {
                    putString(PROCEDURE_ID, navigationAction.procedureId)
                })
            }
        }
    }
}