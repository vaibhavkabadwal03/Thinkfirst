package com.neighborly.thinkfirst.feature.appselection

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppSelectionContent(state: AppSelectionUiState) {

    LazyColumn {
        items(state.installedApps) { item ->
            Text(text = item.appName)
        }
    }
}