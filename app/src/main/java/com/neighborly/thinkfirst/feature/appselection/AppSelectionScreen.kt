package com.neighborly.thinkfirst.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kisaan.tubewell.core.designsystem.components.AppAlertDialog
import com.neighborly.thinkfirst.R
import com.neighborly.thinkfirst.feature.appselection.AppFilter
import com.neighborly.thinkfirst.feature.appselection.AppSelectionContent
import com.neighborly.thinkfirst.feature.appselection.AppSelectionUiState

@Composable
fun AppSelectionScreen(
    uiState: AppSelectionUiState,
    onAppSelectionChanged: (String, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onAppFilterChanged: (AppFilter) -> Unit,
) {
    AppSelectionContent(uiState, onAppSelectionChanged,onSearchQueryChanged, onAppFilterChanged)
}