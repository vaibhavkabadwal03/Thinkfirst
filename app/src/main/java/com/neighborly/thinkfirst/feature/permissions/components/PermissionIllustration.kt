package com.neighborly.thinkfirst.feature.permissions.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme


@Composable
fun PermissionIllustration(
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(
        modifier = modifier.size(180.dp),
        contentAlignment = Alignment.Center
    ) {

        // Soft ambient glow
        Box(
            modifier = Modifier
                .size(150.dp)
                .blur(35.dp)
                .background(
                    color = primaryColor.copy(
                        alpha = 0.16f
                    ),
                    shape = CircleShape
                )
        )

        // Outer ring
        Canvas(
            modifier = Modifier.size(140.dp)
        ) {
            drawCircle(
                color = primaryColor.copy(
                    alpha = 0.12f
                ),
                style = Stroke(width = 2.dp.toPx())
            )

            drawCircle(
                color = primaryColor.copy(
                    alpha = 0.28f
                ),
                radius = size.minDimension / 2f - 8.dp.toPx(),
                style = Stroke(width = 2.dp.toPx())
            )
        }

        // Accessibility icon
        Icon(
            imageVector = Icons.Outlined.Face,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}