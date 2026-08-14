package com.neighborly.thinkfirst.feature.permissions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.neighborly.thinkfirst.R
import com.neighborly.thinkfirst.ui.theme.ThinkFirstGreenSoft

@Composable
fun PermissionStepsCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                MaterialTheme.colorScheme.surface.copy(
                    alpha = 0.65f
                )
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.35f
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(vertical = 10.dp),

        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(R.string.why_accessibility).uppercase(),
            color = ThinkFirstGreenSoft,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        PermissionStepRow(modifier,stringResource(R.string.step_card_title1), stringResource(R.string.step_card_subtitle1), true)
        PermissionStepRow(modifier,stringResource(R.string.step_card_title2), stringResource(R.string.step_card_subtitle2), true)
        PermissionStepRow(modifier,stringResource(R.string.step_card_title3), stringResource(R.string.step_card_subtitle3), false)

    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
private fun PermissionStepsCardPreview() {
    PermissionStepsCard(
        modifier = Modifier.padding(horizontal = 20.dp),
    )
}