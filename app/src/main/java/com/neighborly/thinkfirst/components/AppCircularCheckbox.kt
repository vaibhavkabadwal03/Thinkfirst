package com.neighborly.thinkfirst.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun AppCircularCheckbox(
    checked: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = if (checked) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.outline
                },
                shape = CircleShape
            )
            .background(
                color = if (checked) {
                    MaterialTheme.colorScheme.primary
                } else {
                    Color.Transparent
                },
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
