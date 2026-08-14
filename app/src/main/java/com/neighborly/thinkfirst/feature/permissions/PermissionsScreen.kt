package com.neighborly.thinkfirst.feature.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.neighborly.thinkfirst.feature.permissions.components.EnableAccessibilityButton
import com.neighborly.thinkfirst.feature.permissions.components.PermissionExplanation
import com.neighborly.thinkfirst.feature.permissions.components.PermissionHeader
import com.neighborly.thinkfirst.feature.permissions.components.PermissionIllustration
import com.neighborly.thinkfirst.feature.permissions.components.PermissionStepsCard
import com.neighborly.thinkfirst.feature.permissions.components.ThinkFirstLogo
import com.neighborly.thinkfirst.ui.theme.ThinkFirstGreenDark
import com.neighborly.thinkfirst.ui.theme.ThinkFirstTextMuted

@Composable
fun PermissionsScreen(
    onEnableAccessibilityClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .windowInsetsPadding(
                WindowInsets.statusBars
            )
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        ThinkFirstLogo()

        Spacer(modifier = Modifier.height(10.dp))

        PermissionIllustration()

        Spacer(modifier = Modifier.height(10.dp))

        PermissionHeader()

        Spacer(modifier = Modifier.height(16.dp))

        PermissionExplanation()

        Column(
            modifier = Modifier.padding(
                start = 30.dp,
                end = 30.dp,
                top = 20.dp
            )
        ) {
            PermissionStepsCard()

            Spacer(modifier = Modifier.height(24.dp))

            EnableAccessibilityButton(
                onClick = onEnableAccessibilityClick
            )
        }

        Column(
            modifier = Modifier.padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-10).dp)
        ) {
            Text(
                text = stringResource(R.string.privacy_statement1),
                color = ThinkFirstGreenDark,
                fontSize = 12.sp
            )
            Text(
                text = stringResource(R.string.privacy_statement2),
                color = ThinkFirstTextMuted,
                fontSize = 11.sp
            )
        }

    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
private fun PermissionsScreenPreview() {
    PermissionsScreen(
        onEnableAccessibilityClick = {}
    )
}