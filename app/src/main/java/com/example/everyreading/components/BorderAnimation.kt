package com.example.everyreading.components

import android.icu.text.ListFormatter.Width
import android.view.animation.Animation
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.everyreading.ui.theme.Pink300
import com.example.everyreading.ui.theme.Pink900
import java.time.Duration


@Composable
fun AnimatedBorderCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = 0.dp),
    borderWidth: Dp = 4.dp,
    gradient: Brush = Brush.sweepGradient(listOf(Pink300, Pink900)),
    animationDuration: Int = 10000,
    content: @Composable () -> Unit
){
    val infiniteTransition = rememberInfiniteTransition(label = "Animação de cores infinitas")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Cores infinitas"
    )

    Surface (
        modifier = modifier,
        shape = shape
    ){
        Surface (
            modifier = modifier
                .fillMaxWidth()
                .padding(borderWidth)
                .drawWithContent {
                    rotate(degrees = degrees) {
                        drawCircle(
                            brush = gradient,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn
                        )
                        this@drawWithContent.drawContent()
                    }
                },
            color = Pink900,
            shape = shape
        ) {
            content()
        }
    }
}

@Composable
@Preview
private fun AnimateBorderCardPreview(){
    AnimatedBorderCard (
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        borderWidth = 2.dp,
        gradient = Brush.sweepGradient(
            listOf(
                Pink300,
                Pink900
            )
        ),
        animationDuration = 10000
    ){

    }
}