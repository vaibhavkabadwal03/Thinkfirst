package com.neighborly.thinkfirst.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kisaan.tubewell.core.designsystem.components.AppAlertDialog
import com.neighborly.thinkfirst.R
import com.neighborly.thinkfirst.feature.appselection.AppSelectionContent
import com.neighborly.thinkfirst.feature.appselection.AppSelectionUiState

@Composable
fun AppSelectionScreen(
    uiState: AppSelectionUiState,
    onAppSelectionChanged: (String, Boolean) -> Unit,
    onContinueClick: () -> Unit,
    onAccessibilityDialogDismiss: () -> Unit,
    onAccessibilityConfirm: () -> Unit
) {

    val dialogTitle = stringResource(R.string.accessibility_permission)
    val dialogSubtitle = stringResource(R.string.accessibility_permission_subtitle)

    AppSelectionContent(uiState, onAppSelectionChanged, onContinueClick)

    if (uiState.showAccessibilityDialog) {
        AppAlertDialog(
            onAccessibilityConfirm = onAccessibilityConfirm,
            onAccessibilityDialogDismiss = onAccessibilityDialogDismiss,
            title = dialogTitle,
            subtitle = dialogSubtitle
        )
    }
}