package com.neighborly.thinkfirst.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.neighborly.thinkfirst.R
import com.neighborly.thinkfirst.feature.appselection.AppFilter

@Composable
fun AppFilterSegmentedControl(
    selectedFilter: AppFilter,
    onFilterSelected: (AppFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier.fillMaxWidth()
    ) {

        SegmentedButton(
            selected = selectedFilter == AppFilter.ALL,
            onClick = {
                onFilterSelected(AppFilter.ALL)
            },
            shape = SegmentedButtonDefaults.itemShape(
                index = 0,
                count = 2
            )
        ) {
            Text(stringResource(R.string.all_apps))
        }

        SegmentedButton(
            selected = selectedFilter == AppFilter.SELECTED,
            onClick = {
                onFilterSelected(AppFilter.SELECTED)
            },
            shape = SegmentedButtonDefaults.itemShape(
                index = 1,
                count = 2
            )
        ) {
            Text(stringResource(R.string.selected_apps))
        }
    }
}