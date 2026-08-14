package com.neighborly.thinkfirst.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavRoute {
    @Serializable
    data object Dashboard : AppNavRoute
    @Serializable
    data object AppSelection : AppNavRoute
    @Serializable
    data object Permissions : AppNavRoute
}