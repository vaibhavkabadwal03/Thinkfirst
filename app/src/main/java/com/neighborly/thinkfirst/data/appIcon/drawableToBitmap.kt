package com.neighborly.thinkfirst.data.appIcon

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable


/** Extension Function to convert Drawable to Bitmap Helper Class*/

fun Drawable.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(
        intrinsicWidth.coerceAtLeast(1),
        intrinsicHeight.coerceAtLeast(1),
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)

    setBounds(
        0,
        0,
        canvas.width,
        canvas.height
    )

    draw(canvas)

    return bitmap
}