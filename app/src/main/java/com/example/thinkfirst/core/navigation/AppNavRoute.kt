package com.example.thinkfirst.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavRoute {

    @Serializable
    data object Dashboard : AppNavRoute
    data object AppSelection : AppNavRoute
    data object Permissions : AppNavRoute
    data object Intervention : AppNavRoute
}