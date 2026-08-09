package com.neighborly.thinkfirst.feature.appselection

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.neighborly.thinkfirst.components.AppSelectionRow

@Composable
fun AppSelectionContent(
    state: AppSelectionUiState,
    onAppSelectionChanged: (String, Boolean) -> Unit
) {

    LazyColumn {
        items(
            state.apps,
            key = { it.packageName }) { app ->
            AppSelectionRow(
                app = app,
                isSelected = app.packageName in state.selectedPackages,
                onSelectionChanged = { selected ->
                    onAppSelectionChanged(
                        app.packageName,
                        selected
                    )
                })
        }
    }
}