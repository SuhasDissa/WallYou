package com.bnyro.wallpaper.ui.components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperViewTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onClickInfo: () -> Unit,
    onClickBack: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { onClickBack.invoke() }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Go Back")
            }
        },
        title = { Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = onClickInfo) {
                Icon(imageVector = Icons.Rounded.Info, contentDescription = "Image Info")
            }
        }
    )
}