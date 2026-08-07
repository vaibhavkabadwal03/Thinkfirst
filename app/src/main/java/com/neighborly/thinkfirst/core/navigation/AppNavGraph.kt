package com.neighborly.thinkfirst.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neighborly.thinkfirst.feature.appselection.AppSelectionRoute
import com.neighborly.thinkfirst.feature.dashboard.DashboardRoute

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppNavRoute.Dashboard
    ) {
        composable<AppNavRoute.Dashboard> {
            DashboardRoute(
                onNavigateToAppSelection = {
                    navController.navigate(AppNavRoute.AppSelection)
                },
                onPermissionsClick = {
                    navController.navigate(AppNavRoute.Permissions)
                }
            )
        }

        composable<AppNavRoute.AppSelection> {
            AppSelectionRoute()
        }
    }
}