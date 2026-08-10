package com.neighborly.thinkfirst.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.material3.Icon
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.neighborly.thinkfirst.R
import com.neighborly.thinkfirst.data.appIcon.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AppIcon(
    packageName: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var icon by remember(packageName) {
        mutableStateOf<Drawable?>(null)
    }

    LaunchedEffect(packageName) {
        icon = withContext(Dispatchers.IO) {
            runCatching {
                context.packageManager.getApplicationIcon(packageName)
            }.getOrNull()
        }
    }

    if (icon != null) {
        Image(
            bitmap = icon!!.toBitmap().asImageBitmap(),
            contentDescription = null,
            modifier = modifier
        )
    } else {
        Icon(
            painter = painterResource(R.drawable.ic_app_placeholder),
            contentDescription = null,
            modifier = modifier
        )
    }
}