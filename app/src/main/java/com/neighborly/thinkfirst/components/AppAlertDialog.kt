package com.kisaan.tubewell.core.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.neighborly.thinkfirst.R

@Composable
fun AppAlertDialog(
    onAccessibilityConfirm: () -> Unit,
    onAccessibilityDialogDismiss: () -> Unit,
    title: String,
    subtitle: String,
) {
    AlertDialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        title = { Text(text = title) },
        text = { Text(text = subtitle) },
        confirmButton = {
            Button(
                onClick = { onAccessibilityConfirm() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            Button(
                onClick = { onAccessibilityDialogDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = stringResource(R.string.cancel))
            }
        },
    )
}