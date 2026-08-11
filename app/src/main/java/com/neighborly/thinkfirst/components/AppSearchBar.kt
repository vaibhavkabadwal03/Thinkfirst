package com.neighborly.thinkfirst.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.neighborly.thinkfirst.R
import com.neighborly.thinkfirst.feature.appselection.AppSelectionUiState

@Composable
fun AppSearchBar(state: AppSelectionUiState, onSearchQueryChanged: (String) -> Unit) {
    OutlinedTextField(
        value = state.searchQuery,
        onValueChange = onSearchQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        placeholder = {
            Text(
                text = stringResource(R.string.search_apps)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (state.searchQuery.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onSearchQueryChanged("")
                    }
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp)
    )
}