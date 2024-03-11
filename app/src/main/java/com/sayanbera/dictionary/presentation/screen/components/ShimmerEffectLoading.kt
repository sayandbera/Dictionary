package com.sayanbera.dictionary.presentation.screen.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffectLoading() {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.onBackground.copy(0.1f),
        MaterialTheme.colorScheme.secondaryContainer.copy(0.7f),
        MaterialTheme.colorScheme.onBackground.copy(0.1f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerGridItem(brush = brush)
}

@Composable
private fun ShimmerGridItem(brush: Brush) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp, top = 32.dp, bottom = 8.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(brush)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Spacer(
                modifier = Modifier
                    .height(25.dp)
                    .clip(RoundedCornerShape(50))
                    .fillMaxWidth()
                    .background(brush)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(
                modifier = Modifier
                    .height(12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth(0.25f)
                    .background(brush)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .background(brush)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .background(brush)
                )
            }
        }
    }
}
