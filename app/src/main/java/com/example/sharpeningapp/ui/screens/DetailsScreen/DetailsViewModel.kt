package com.example.sharpeningapp.ui.screens.DetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharpeningapp.data.HighScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val repository: HighScoreRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    private val _selectedTab = MutableStateFlow<DetailsTab>(DetailsTab.Skills)
    val selectedTab: StateFlow<DetailsTab> = _selectedTab.asStateFlow()

    fun getPlayer(username: String) {
        viewModelScope.launch {
            val player = repository.getPlayerScores(username)
            if (player != null) {
                _uiState.value = DetailsUiState.Success(player)
            } else {
                _uiState.value = DetailsUiState.Error
            }
        }
    }

    fun selectTab(tab: DetailsTab) {
        _selectedTab.value = tab
    }
}