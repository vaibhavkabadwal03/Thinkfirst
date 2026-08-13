package com.neighborly.thinkfirst.feature.intervention

sealed interface InterventionEffect {

    data class OpenApp(
        val packageName: String
    ) : InterventionEffect

    data object Close : InterventionEffect
}