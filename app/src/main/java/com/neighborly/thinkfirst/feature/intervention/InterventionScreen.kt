package com.neighborly.thinkfirst.feature.intervention

import android.graphics.BlurMaskFilter
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neighborly.thinkfirst.R
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
@Composable
fun InterventionScreen(
    state: InterventionUiState,
    onOpenClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        AnimatedContent(
            targetState = state.isCountdownFinish,
            label = "intervention_content",
        ) { countdownFinished ->

            if (!countdownFinished) {
                CountdownContent(state)

            } else {
                DecisionContent(
                    state = state,
                    onOpenClick = onOpenClick,
                    onCloseClick = onCloseClick
                )
            }
        }
    }
}

@Composable
fun DecisionContent(state: InterventionUiState, onOpenClick: () -> Unit, onCloseClick: () -> Unit) {

    val cardShape = RoundedCornerShape(28.dp)
    val infiniteTransition = rememberInfiniteTransition(
        label = "floating_icon"
    )

    val floatingOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -6f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floating_offset"
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(520.dp)) {
            // Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
                    .clip(cardShape)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.04f),
                                Color.Black.copy(alpha = 0.95f)
                            )
                        )
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.28f),
                        shape = cardShape
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 72.dp,
                            bottom = 28.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(
                            R.string.intervention_confirmation_title,
                            state.appName
                        ),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        text = stringResource(
                            R.string.intervention_message
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )

                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )

                    Button(
                        onClick = onOpenClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.open).uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    OutlinedButton(
                        onClick = onCloseClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.close).uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }

            // Floating app icon
            AppIconHexagon(
                state = state,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = floatingOffset.dp)
            )
        }
    }
}

@Composable
fun CountdownContent(state: InterventionUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CountdownRing(
            countDown = state.countDown
        )

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        getIcon(state)

        Text(
            text = "OPENING ${state.appName.uppercase()}",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        Text(
            text = "Take a moment.",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Pause. Think. Decide.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(48.dp)
        )

        Text(
            text = "PAUSE & THINK",
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.10f),
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Wait a brief moment to\nconsider your choice.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CountdownRing(countDown: Int) {
    val progress = countDown / 5f

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(180.dp)
    ) {

        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.10f),
            trackColor = Color.Transparent,
            strokeWidth = 12.dp
        )

        // Middle glow
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.20f),
            trackColor = Color.Transparent,
            strokeWidth = 8.dp
        )

        // Main neon ring
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.10f),
            strokeWidth = 2.dp
        )

        Text(
            text = countDown.toString(),
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun getIcon(state: InterventionUiState) {
    AppIconHexagon(
        state = state
    )

    Spacer(
        modifier = Modifier.height(16.dp)
    )
}

@Composable
private fun AppIconHexagon(
    state: InterventionUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {

        // Glow layers
        HexagonGlow(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.10f),
            strokeWidth = 14f
        )

        HexagonGlow(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.20f),
            strokeWidth = 8f
        )

        // Main hexagonal border
        HexagonGlow(
            modifier = Modifier
                .fillMaxSize()
                .padding(9.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 3f
        )

        // App icon
        state.appIcon?.let { icon ->

            Image(
                bitmap = icon,
                contentDescription = state.appName,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
private fun HexagonGlow(
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Float
) {
    Canvas(modifier = modifier) {

        val path = createRoundedHexagonPath(
            width = size.width,
            height = size.height,
            cornerRadius = size.minDimension * 0.12f
        )

        // Smooth glow
        drawIntoCanvas { canvas ->

            val paint = Paint().apply {
                this.color = color
                this.style = PaintingStyle.Stroke
                this.strokeWidth = strokeWidth
                this.asFrameworkPaint().apply {
                    maskFilter = BlurMaskFilter(
                        18f,
                        BlurMaskFilter.Blur.NORMAL
                    )
                }
            }

            canvas.drawPath(path, paint)
        }

        // Sharp neon edge
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = strokeWidth
            )
        )
    }
}

private fun createRoundedHexagonPath(
    width: Float,
    height: Float,
    cornerRadius: Float
): Path {

    val centerX = width / 2f
    val centerY = height / 2f

    val radius = minOf(width, height) / 2f

    val points = List(6) { index ->
        val angle = Math.toRadians(
            (60 * index - 30).toDouble()
        )

        Offset(
            x = centerX + radius * 0.85f * kotlin.math.cos(angle).toFloat(),
            y = centerY + radius * 0.85f * kotlin.math.sin(angle).toFloat()
        )
    }

    val path = Path()

    for (i in points.indices) {

        val current = points[i]
        val previous = points[(i - 1 + points.size) % points.size]
        val next = points[(i + 1) % points.size]

        val start = Offset(
            x = current.x + (previous.x - current.x) * 0.15f,
            y = current.y + (previous.y - current.y) * 0.15f
        )

        val end = Offset(
            x = current.x + (next.x - current.x) * 0.15f,
            y = current.y + (next.y - current.y) * 0.15f
        )

        if (i == 0) {
            path.moveTo(start.x, start.y)
        } else {
            path.lineTo(start.x, start.y)
        }

        path.quadraticTo(
            current.x,
            current.y,
            end.x,
            end.y
        )
    }

    path.close()

    return path
}

/*
Column(
horizontalAlignment = Alignment.CenterHorizontally
) {
    getIcon(state)

    Text(
        text = stringResource(
            id = R.string.intervention_confirmation_title,
            state.appName
        ),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )

    Spacer(
        modifier = Modifier.height(32.dp)
    )

    Row(horizontalArrangement = Arrangement.Center) {
        Button(
            onClick = onOpenClick,
            modifier = Modifier
                .weight(0.5f)
                .padding(end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = stringResource(R.string.open))
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = onCloseClick,
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = stringResource(R.string.close))
        }
    }*/
