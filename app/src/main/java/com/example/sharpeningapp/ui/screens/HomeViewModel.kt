package com.example.sharpeningapp.ui.screens


import android.util.Log
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sharpeningapp.data.HighScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: HighScoreRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun getLeaders(table: Int, category: Int) {
        viewModelScope.launch {
            val leaders = repository.getLeaders(table, category, 50)
            if (leaders != null) {
                _uiState.value = HomeUiState.Success(leaders)
            } else {
                _uiState.value = HomeUiState.Error
            }
        }
    }

    fun onQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onSearch() {
        val query = _searchQuery.value
        if (query.isNotBlank()) {
            Log.i("!@#$", "mock navigation to details screen")
        }
    }


}