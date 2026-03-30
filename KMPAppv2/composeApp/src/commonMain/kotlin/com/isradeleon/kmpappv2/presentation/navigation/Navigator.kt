package com.isradeleon.kmpappv2.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.toRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class Navigator {
    private val scope = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var parentNavController: NavController
    private lateinit var nestedNavController: NavController

    private var nestedNavControllerObserveJob: Job? = null
    private var parentNavControllerObserveJob: Job? = null

    val currentScreen = MutableStateFlow<Screen?>(null)
    val currentTab = MutableStateFlow<Screen?>(null)
    val isHome = MutableStateFlow(false)

    fun setParentNavController(navController: NavController) {
        parentNavController = navController

        parentNavControllerObserveJob?.cancel()
        parentNavControllerObserveJob = scope.launch {
            navController.currentBackStackEntryFlow.map { backStackEntry ->
                val screen = Screen.fromBackStackEntry(backStackEntry)
                currentTab.value = screen
            }.collect()
        }
    }

    fun setNestedNavController(navController: NavController) {
        nestedNavController = navController

        nestedNavControllerObserveJob?.cancel()
        nestedNavControllerObserveJob = scope.launch {
            navController.currentBackStackEntryFlow.map { backStackEntry ->
                val screen = Screen.fromBackStackEntry(backStackEntry)
                currentScreen.value = screen
                isHome.value = screen == Screen.FavoriteCoinsTab
            }.collect()
        }
    }

    fun navigateToTab(screen: Screen) {
        parentNavController.navigate(screen) {
            parentNavController.graph.startDestinationRoute?.let { startRoute ->
                popUpTo(startRoute) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigate(screen: Screen) {
        nestedNavController.navigate(screen)
    }

    fun navigateBack() {
        if (!nestedNavController.popBackStack()) {
            parentNavController.popBackStack()
        }
    }

    companion object {
        val NAVIGATION_TABS = listOf(
            Screen.FavoriteCoinsTab,
            Screen.CoinsListTab
        )
    }
}