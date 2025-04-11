package com.example.sharpeningapp.ui.screens.HomeScreen


import android.util.Log
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
class HomeViewModel @Inject constructor(
    val repository: HighScoreRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    private val _lastFetch = MutableStateFlow<QueryState>(QueryState())
    val lastFetch: StateFlow<QueryState> = _lastFetch.asStateFlow()

    fun getLeaders(table: Int, category: Int) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            _lastFetch.value = QueryState(table, category)
            val leaders = repository.getLeaders(table, category, 50)
            if (leaders != null) {
                _uiState.value = HomeUiState.Success(leaders)
            } else {
                _uiState.value = HomeUiState.Error
            }
        }
    }

    fun onRetryLoad(QueryState: QueryState = lastFetch.value) {
        getLeaders(QueryState.table, QueryState.category)
    }

    fun onQueryChange(query: String) {
        _searchQuery.value = query
    }
}