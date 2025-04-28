package com.example.sharpeningapp.ui.screens.HomeScreen


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharpeningapp.data.HighScoreRepository
import com.example.sharpeningapp.data.toRanking
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
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
    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet.asStateFlow()
    private val _subject = MutableStateFlow("")
    val subject: StateFlow<String> = _subject.asStateFlow()
    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()
    private val _sendEmailEvent = MutableStateFlow(false)
    val sendEmailEvent: StateFlow<Boolean> = _sendEmailEvent.asStateFlow()

    init {
        viewModelScope.launch {
            refreshScores()
            observeLeaders()
        }
    }

    fun observeLeaders() {
        viewModelScope.launch {
            try {
                repository.top50.collect {
                    if (it.isNotEmpty()) {
                        _uiState.value = HomeUiState.Success(it.map { leader ->
                            leader.toRanking()
                        })
                    }
                }
            } catch (e: IOException) {
                _uiState.value = HomeUiState.Error
            }
        }
    }

    fun refreshScores() {
        viewModelScope.launch {
            while (true) {
                try {
                    repository.updateLeaders()
                } catch (e: Exception) {
                    Log.e("API_ERROR", "Error refreshing leaders")
                    _uiState.value = HomeUiState.Error
                    break
                }
                delay(60 * 60 * 1000)
            }
        }
    }


    fun onRetryLoad(QueryState: QueryState = lastFetch.value) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                repository.updateLeaders()
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error refreshing leaders")
                _uiState.value = HomeUiState.Error
            }
        }
    }

    fun onQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun toggleBottomSheet() {
        _showBottomSheet.value = !_showBottomSheet.value
    }

    fun onSubjectChange(subject: String) {
        _subject.value = subject
    }

    fun onMessageChange(message: String) {
        _message.value = message
    }

    fun onRequestEmailSend() {
        _sendEmailEvent.value = true
    }

    fun onSendEmailHandled() {
        _sendEmailEvent.value = false
    }
}