package com.example.sharpeningapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Banner(
    fontColor: Color = Color.White,
    title: String = "Old School HighScores",
    subText: String? = null,
    onSubTextClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(75.dp)
) {
    Box(
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = fontColor,
                fontSize = 24.sp
            )
            if (subText != null) {
                Text(
                    text = subText,
                    modifier = Modifier
                        .clickable { onSubTextClick() },
                    color = Color.Green,
                    fontSize = 16.sp
                )
            }
        }
    }
}