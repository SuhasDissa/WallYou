package com.bnyro.wallpaper.ui.components.bottombar

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bnyro.wallpaper.db.obj.Wallpaper

@Composable
fun BottomBar(modifier: Modifier = Modifier, wallpaper: Wallpaper, onClickEdit: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WallpaperViewActions(wallpaper = wallpaper, onClickEdit = onClickEdit)
    }
}