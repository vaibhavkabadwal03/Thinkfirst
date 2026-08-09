package com.neighborly.thinkfirst.feature.appselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.neighborly.thinkfirst.R
import com.neighborly.thinkfirst.components.AppSelectionRow

@Composable
fun AppSelectionContent(
    state: AppSelectionUiState,
    onAppSelectionChanged: (String, Boolean) -> Unit,
    onContinueClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.onPrimary)
            .padding(bottom = 16.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
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
        Button(
            onClick = onContinueClick,
            enabled = state.selectedPackages.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(stringResource(R.string.continue_button))
        }
    }
}