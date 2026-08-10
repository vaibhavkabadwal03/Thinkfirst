package com.neighborly.thinkfirst.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neighborly.thinkfirst.domain.model.InstalledApp

@Composable
fun AppSelectionRow(
    app: InstalledApp,
    isSelected: Boolean,
    onSelectionChanged: (Boolean) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(40.dp)),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    onSelectionChanged(!isSelected)
                }
                .border(
                    width = .5.dp,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(12.dp)
                )
                .shadow(
                    elevation = if (isSelected) 8.dp else 0.dp,
                    shape = RoundedCornerShape(6.dp),
                    ambientColor = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Transparent
                    },
                    spotColor = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Transparent
                    }
                ),
            elevation = cardElevation(
                defaultElevation = 6.dp,
                pressedElevation = 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppIcon(
                    packageName = app.packageName,
                    modifier = Modifier.size(48.dp)
                )

                Text(
                    text = app.appName,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .weight(2f),
                    fontSize = 18.sp,
                )
                AppCircularCheckbox(
                    checked = isSelected,
                )
            }
        }
    }
}