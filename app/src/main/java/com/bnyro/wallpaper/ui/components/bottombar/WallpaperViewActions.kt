package com.bnyro.wallpaper.ui.components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.SaveAlt
import androidx.compose.material.icons.rounded.Wallpaper
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun WallpaperViewActions(
    color: Color = Color.White,
    onClickEdit: () -> Unit,
    onClickDownload: () -> Unit,
    onClickWallpaper: () -> Unit,
    onClickFavourite: () -> Unit,
    isFavourite: Boolean
) {
    IconWithText(
        imageVector = Icons.Rounded.Wallpaper,
        title = "Wallpaper",
        color = color
    ) {
        onClickWallpaper.invoke()
    }
    IconWithText(
        imageVector = Icons.Rounded.SaveAlt,
        title = "Save",
        color = color
    ) {
        onClickDownload.invoke()
    }
    IconWithText(
        imageVector = Icons.Rounded.Edit,
        title = "Edit",
        color = color
    ) {
        onClickEdit.invoke()
    }
    IconWithText(
        imageVector = if (isFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
        title = "Favourite",
        color = color
    ) {
        onClickFavourite.invoke()
    }
}
