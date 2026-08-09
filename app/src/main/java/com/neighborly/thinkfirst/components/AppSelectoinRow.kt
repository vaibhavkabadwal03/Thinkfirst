package com.neighborly.thinkfirst.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neighborly.thinkfirst.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppSelectionRow() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
            Image(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp),
            )
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(2f),
                fontSize = 18.sp,
            )
            Checkbox(
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}