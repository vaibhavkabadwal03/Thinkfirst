package com.neighborly.thinkfirst.feature.appselection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neighborly.thinkfirst.components.AppSelectionScreen

@Composable
fun AppSelectionRoute(viewModel: AppSelectionViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    AppSelectionScreen(
        uiState = uiState,
        onAppSelectionChanged = viewModel::onAppSelectionChanged,
        onAppFilterChanged = viewModel::onAppFilterChanged,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
//        onContinueClick = viewModel::onContinueClicked,
//        onAccessibilityDialogDismiss = viewModel::onAccessibilityDialogDismiss,
        /*onAccessibilityConfirm = {
            context.startActivity(
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            )
        }*/

    )
}