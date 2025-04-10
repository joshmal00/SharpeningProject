package com.example.sharpeningapp.ui.screens.DetailsScreen

import com.example.sharpeningapp.network.Player

sealed interface DetailsUiState {
    data class Success(
        val player: Player
    ) : DetailsUiState
    object Error: DetailsUiState
    object Loading: DetailsUiState
}