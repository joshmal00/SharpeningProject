package com.example.sharpeningapp.ui.screens.DetailsScreen

import com.example.sharpeningapp.network.Player

sealed interface DetailsUiState {
    data class Success(
        val player: Player
    ) : DetailsUiState
    data class Error(
        val errorMessage: String
    ): DetailsUiState
    object Loading: DetailsUiState
}