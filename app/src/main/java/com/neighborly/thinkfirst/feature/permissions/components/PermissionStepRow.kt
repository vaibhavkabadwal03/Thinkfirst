package com.neighborly.thinkfirst.feature.permissions.components

import androidx.compose.material.icons.filled.Star
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neighborly.thinkfirst.ui.theme.ThinkFirstGreen
import com.neighborly.thinkfirst.ui.theme.ThinkFirstGreenSoft
import com.neighborly.thinkfirst.ui.theme.ThinkFirstTextSecondary

@Composable
fun PermissionStepRow(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    showBottomDivider: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth().padding(start = 10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end= 12.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Icon
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = ThinkFirstGreen,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .border(
                        width = 1.dp,
                        color = ThinkFirstGreen.copy(alpha = 0.6f),
                        shape = CircleShape
                    )
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.size(18.dp))

            // Text
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = subtitle,
                    color = ThinkFirstTextSecondary,
                    fontSize = 11.sp,
                    lineHeight = 14.sp
                )

            }
        }
        if (showBottomDivider){
            HorizontalDivider(
                color = ThinkFirstGreenSoft.copy(alpha = 0.1f),
                thickness = 1.dp,
                modifier = Modifier.padding(start = 55.dp, end = 20.dp)
            )
        }


    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
private fun PermissionStepRowPreview() {
    PermissionStepRow(
        modifier = Modifier.padding(horizontal = 20.dp),
        title = "Detect selected apps",
        subtitle = "We observe when the apps you choose are opened."
    )
}