package ru.pervov.lovenail

import android.app.Activity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.pervov.lovenail.client_create_screen.fragment.CLIENT_ID
import ru.pervov.lovenail.procedure_create_screen.fragment.PROCEDURE_ID
import ru.pervov.lovenail.utils.NavigationAction
import ru.pervov.lovenail.utils.NavigationHolder

class NavigationHolderImpl(
    activity: Activity
) : NavigationHolder {

    private val navController by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    private val navigationBar by lazy {
        activity.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
    }

    fun setUp() {
        navigationBar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id) {
                R.id.clientListFragment,
                R.id.procedureListFragment,
                R.id.dayCalendarFragment -> {
                    navigationBar.isVisible = true
                }
                else -> {
                    navigationBar.isVisible = false
                }
            }
        }
    }

    override fun doNavigation(navigationAction: NavigationAction) {
        when (navigationAction) {

            // client
            is NavigationAction.OpenCreateOrUpdateClient -> {
                navController.navigate(R.id.action_global_clientCreateFragment, args = Bundle().apply {
                    putString(CLIENT_ID, navigationAction.clientId)
                })
            }
            is NavigationAction.OpenClientList -> navController.navigate(R.id.action_global_clientListFragment)

            // calendar
            is NavigationAction.OpenWeekCalendar -> navController.navigate(R.id.action_global_weekCalendarFragment)
            is NavigationAction.OpenDayCalendar -> navController.navigate(R.id.action_global_dayCalendarFragment)

            // procedure
            is NavigationAction.OpenProcedureList -> navController.navigate(R.id.action_global_procedureListFragment)
            is NavigationAction.OpenCreateOrUpdateProcedure -> {
                navController.navigate(
                    R.id.action_global_procedureCreateFragment, args = Bundle().apply {
                        putString(PROCEDURE_ID, navigationAction.procedureId)
                    })
            }

            // mock
            is NavigationAction.OpenCreateMock -> navController.navigate(R.id.action_global_createMockFragment)
        }
    }
}