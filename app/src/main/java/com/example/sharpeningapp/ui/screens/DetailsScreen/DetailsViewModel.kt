package com.example.sharpeningapp.ui.screens.DetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharpeningapp.data.HighScoreRepository
import com.example.sharpeningapp.data.PlayerResult
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
    private val _currentUsername = MutableStateFlow<String>("")
    val currentUsername: StateFlow<String> = _currentUsername.asStateFlow()

    fun getPlayer(username: String) {
        viewModelScope.launch {
            _uiState.value = DetailsUiState.Loading
            _currentUsername.value = username
            val playerResult = repository.getPlayerScores(username)
            when (playerResult) {
                is PlayerResult.Success -> _uiState.value =
                    DetailsUiState.Success(playerResult.player)

                is PlayerResult.ApiError -> _uiState.value =
                    DetailsUiState.Error(playerResult.message)

                is PlayerResult.NetworkError -> _uiState.value =
                    DetailsUiState.Error(playerResult.message)
            }
        }
    }

    fun onRetryLoad(username: String = _currentUsername.value) {
        getPlayer(username)
    }

    fun selectTab(tab: DetailsTab) {
        _selectedTab.value = tab
    }
}