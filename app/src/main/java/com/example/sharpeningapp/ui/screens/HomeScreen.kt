package com.example.sharpeningapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        CompositionLocalProvider(LocalContentColor provides Color.White) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Banner()
//                SearchBar() { }
            }
        }
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .border(2.dp, Color.Red)
) {
    Box(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = "Old School HighScores",
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier,
    query: String,
    onQueryChance: (String) -> Unit,
    onSearch: () -> Unit,
) {
    Box() {
//        TextField()
    }
}