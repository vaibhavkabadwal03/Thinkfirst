package com.neighborly.thinkfirst.feature.permissions.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neighborly.thinkfirst.ui.theme.ThinkFirstGreen
import com.neighborly.thinkfirst.ui.theme.ThinkFirstGreenSoft

@Composable
fun ThinkFirstLogo() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .border(
                    width = 2.dp,
                    color = ThinkFirstGreen,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = ThinkFirstGreen,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = "Think",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "First",
            color = ThinkFirstGreenSoft,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}