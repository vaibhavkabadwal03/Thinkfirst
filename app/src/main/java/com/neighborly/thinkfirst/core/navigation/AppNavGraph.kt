package com.neighborly.thinkfirst.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neighborly.thinkfirst.feature.appselection.AppSelectionRoute
import com.neighborly.thinkfirst.feature.dashboard.DashboardRoute
import com.neighborly.thinkfirst.feature.permissions.PermissionsRoute

@Composable
fun AppNavGraph(
    isAccessibilityEnabled: Boolean
) {
    key(isAccessibilityEnabled) {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = if (isAccessibilityEnabled) {
                AppNavRoute.Dashboard
            } else {
                AppNavRoute.Permissions
            }
        ) {
            composable<AppNavRoute.Permissions> {
                PermissionsRoute()
            }

            composable<AppNavRoute.Dashboard> {
                DashboardRoute(
                    onNavigateToAppSelection = {
                        navController.navigate(AppNavRoute.AppSelection)
                    }
                )
            }

            composable<AppNavRoute.AppSelection> {
                AppSelectionRoute()
            }
        }
    }
}