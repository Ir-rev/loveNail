package ru.pervov.lovenail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.pervov.lovenail.toolbar.ToolBarHolder
import ru.pervov.lovenail.utils.NavigationAction
import ru.pervov.lovenail.utils.NavigationHolder

class MainActivity : AppCompatActivity(), NavigationHolder, ToolBarHolder {

    private val navigationHolderImpl = NavigationHolderImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigationHolderImpl.setUp()
    }

    override fun doNavigation(navigationAction: NavigationAction) {
        navigationHolderImpl.doNavigation(navigationAction)
    }

    override fun changeToolbarTitle(newTitle: String) {
        navigationHolderImpl.changeToolbarTitle(newTitle = newTitle)
    }
}