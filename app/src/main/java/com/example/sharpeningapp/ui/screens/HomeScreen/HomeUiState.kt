package com.example.sharpeningapp.ui.screens.HomeScreen

import com.example.sharpeningapp.network.Ranking

sealed interface HomeUiState {
    data class Success(
        val rankings: List<Ranking>
    ) : HomeUiState
    object Error: HomeUiState
    object Loading: HomeUiState
}