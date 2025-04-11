package com.example.sharpeningapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorScreen(
    onRetry: () -> Unit,
    message: String = "Unable to load data"
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(message, textAlign = TextAlign.Center)
            Text(
                "retry",
                color = Color.Blue,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        onRetry()
                    },
            )
        }
    }
}