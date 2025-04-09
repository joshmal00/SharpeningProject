package com.example.sharpeningapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.getLeaders(0,0)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CompositionLocalProvider(LocalContentColor provides Color.White) {
                    Banner()
                    SearchBar(
                        query = viewModel.searchQuery.collectAsState().value,
                        onQueryChange = { viewModel.onQueryChange(it) },
                        onSearch = { viewModel.onSearch() },
                        modifier = Modifier
                    )
                }
                ResultCard(
                    viewModel = viewModel,
                    modifier = Modifier
                )
            }
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
) {
    Box(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = "Old School HighScores",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    Box(
        modifier = Modifier
            .border(2.dp, Color.DarkGray),
    ) {
        TextField(
            value = query,
            onValueChange = { onQueryChange(it) },
            placeholder = { Text("Search") },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = onSearch) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.White,
                containerColor = Color.Black,
                cursorColor = Color.White,
                focusedTrailingIconColor = Color.White,
                unfocusedTrailingIconColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun ResultCard(
    modifier: Modifier,
    viewModel: HomeViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFbca46d))
            .border(4.dp, Color(0xFF382418))
            .padding(16.dp)
    ) {
        val uiState = viewModel.uiState.collectAsState().value
        when (uiState) {
            is HomeUiState.Success -> {
                val leaders = uiState.rankings
                Column() {
                    Text(
                        "Top 50 Overall Highscores",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 16.dp),
                    ) {
                        Text(
                            "Rank",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(.5f)
                        )
                        Text(
                            "Name",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "Score",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        items(leaders, span = { GridItemSpan(3) }) { leader ->
                            Row() {
                                Text(
                                    text = leader.rank,
                                    modifier = Modifier
                                        .padding(top = 12.dp)
                                        .weight(.5f),
                                    textAlign = TextAlign.Center,
                                )
                                Text(
                                    text = leader.name,
                                    modifier = Modifier
                                        .padding(top = 12.dp)
                                        .weight(1f)
                                        .clickable {
                                            Log.i(
                                                "!@#$",
                                                "mock navigation to details screen for ${leader.name}"
                                            )
                                        },
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = leader.score,
                                    modifier = Modifier
                                        .padding(top = 12.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }
            HomeUiState.Error -> {
                Text("Error: Could not load data")
            }
            HomeUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}