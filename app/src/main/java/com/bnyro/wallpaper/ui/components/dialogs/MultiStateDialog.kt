package com.bnyro.wallpaper.ui.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.bnyro.wallpaper.enums.MultiState

@Composable
fun MultiStateDialog(state: MultiState, title: String, onDismiss: () -> Unit) {
    if (state != MultiState.IDLE) {
        AlertDialog(onDismissRequest = { }, confirmButton = {
            TextButton(
                onClick = { onDismiss.invoke() },
                enabled = state != MultiState.RUNNING
            ) {
                Text(text = "Done")
            }
        }, title = {
            Text(text = title)
        }, text = {
            Column {
                when (state) {
                    MultiState.RUNNING -> LinearProgressIndicator()
                    MultiState.SUCCESS -> Text(
                        text = "Success",
                        style = MaterialTheme.typography.titleMedium
                    )

                    else -> Text("Error", style = MaterialTheme.typography.titleMedium)
                }
            }
        })
    }
}