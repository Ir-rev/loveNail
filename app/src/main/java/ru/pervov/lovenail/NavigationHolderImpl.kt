package ru.pervov.lovenail

import android.app.Activity
import androidx.navigation.Navigation
import ru.pervov.utils.NavigationAction
import ru.pervov.utils.NavigationHolder

class NavigationHolderImpl(
    activity: Activity
) : NavigationHolder {

    private val navigation by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    override fun doNavigation(navigationAction: NavigationAction) {
        when (navigationAction) {
            is NavigationAction.OpenClientList -> {
                navigation.navigate(R.id.action_global_clientListFragment)
            }

            is NavigationAction.OpenCreateClient -> {
                navigation.navigate(R.id.action_global_clientCreateFragment)
            }
        }
    }
}