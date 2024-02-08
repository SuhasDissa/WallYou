package com.bnyro.wallpaper.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bnyro.wallpaper.db.obj.Wallpaper
import com.bnyro.wallpaper.ui.components.bottombar.BottomBar
import com.bnyro.wallpaper.util.rememberZoomState
import com.bnyro.wallpaper.util.zoomArea
import com.bnyro.wallpaper.util.zoomImage

@Composable
fun WallpaperView(
    wallpaper: Wallpaper,
    showUiState: () -> MutableState<Boolean>
) {
    var showUi by showUiState()
    var selectedWallpaper by remember { mutableStateOf<Wallpaper?>(null) }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTapGestures {
                    showUi = !showUi
                }
            },
        contentAlignment = Alignment.Center
    ) {
        val zoomState = rememberZoomState()

        val alpha by animateFloatAsState(
            targetValue = if (showUi) 0.5f else 0f,
            label = "backgroundAlpha",
            animationSpec = tween(500)
        )
        AsyncImage(
            model = wallpaper.thumb,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .blur(50.dp)
                .alpha(alpha)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zoomArea(zoomState),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = wallpaper.thumb,
                contentDescription = "Wallpaper",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .zoomImage(zoomState)
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black)
                        )
                    )
                    .align(Alignment.BottomCenter)
            ) {
                AnimatedVisibility(
                    visible = showUi
                ) {
                    BottomBar(
                        modifier = Modifier.padding(top = 30.dp),
                        wallpaper,
                        onClickEdit = {
                            selectedWallpaper = wallpaper
                        }
                    )
                }
            }
        }
    }
    selectedWallpaper?.let {
        WallpaperPreview(wallpaper = it) {
            selectedWallpaper = null
        }
    }
}
