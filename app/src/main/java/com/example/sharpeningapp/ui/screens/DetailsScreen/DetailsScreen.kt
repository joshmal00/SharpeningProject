package com.example.sharpeningapp.ui.screens.DetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharpeningapp.network.Activity
import com.example.sharpeningapp.network.Skill
import com.example.sharpeningapp.ui.components.Banner

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    modifier: Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.getPlayer("swaybabyj")
    }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black,
            ) {
                val selectedTab = viewModel.selectedTab.collectAsState().value
                NavigationBarItem(
                    selected = selectedTab is DetailsTab.Skills,
                    onClick = { viewModel.selectTab(DetailsTab.Skills) },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = "Skills",
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.White,
                        indicatorColor = Color.White
                    ),
                    label = { Text("Skills", color = Color.White)},
                )
                NavigationBarItem(
                    selected = selectedTab is DetailsTab.Activities,
                    onClick = { viewModel.selectTab(DetailsTab.Activities) },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Activities") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.White,
                        indicatorColor = Color.White
                    ),
                    label = { Text("Activities", color = Color.White) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding),
        ) {
            Column() {
                Banner(text = "Player Name")
                PlayerScoresCard(
                    viewModel = viewModel,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun PlayerScoresCard(
    viewModel: DetailsViewModel,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFbca46d))
            .border(4.dp, Color(0xFF382418))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        val uiState = viewModel.uiState.collectAsState().value
        when (uiState) {
            is DetailsUiState.Success -> {
                when (viewModel.selectedTab.collectAsState().value) {
                    is DetailsTab.Skills -> SkillsGrid(uiState.player.skills)
                    is DetailsTab.Activities -> ActivitiesGrid(uiState.player.activities)
                }
            }
            is DetailsUiState.Error -> {
                Text("Error: Could not load data")
            }
            is DetailsUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun SkillsGrid(skills: List<Skill>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize()
    ) {
        item { Text("Skill", fontWeight = FontWeight.Bold) }
        item {
            Text(
                "Rank",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        item {
            Text(
                "Level",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        item { Text("XP", fontWeight = FontWeight.Bold) }

        items(skills, span = { GridItemSpan(4) }) { skill ->
            Row(modifier = Modifier.padding(bottom = 4.dp)) {
                Text(skill.name, modifier = Modifier.weight(1f))
                Text(skill.rank.toString(), modifier = Modifier.weight(1f))
                Text(skill.level.toString(), modifier = Modifier.weight(.5f))
                Text(skill.xp.toString(), modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ActivitiesGrid(activities: List<Activity>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize()
    ) {
        item { Text("Activity", fontWeight = FontWeight.Bold) }
        item { Text("Rank", fontWeight = FontWeight.Bold) }
        item { Text("Score", fontWeight = FontWeight.Bold) }
        items(activities.flatMap { listOf(it.name, it.rank, it.score) }) {
            Text(it.toString(), modifier = Modifier.padding(bottom = 4.dp))
        }
    }
}