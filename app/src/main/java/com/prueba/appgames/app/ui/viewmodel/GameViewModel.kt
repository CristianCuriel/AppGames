package com.prueba.appgames.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.appgames.app.domain.GameUseCase
import com.prueba.appgames.app.ui.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel() : ViewModel() {

    var getGamesUseCase = GameUseCase()

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _optionSelected = MutableStateFlow<String>("")
    val optionSelected: StateFlow<String> = _optionSelected.asStateFlow()

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Loading)
    val uiState: StateFlow<GameUiState> = _uiState

    init {
        onCreate()
    }

    fun onshowDialog() {
        _showDialog.value = !_showDialog.value
    }

    fun selectedFilter(s: String) {
        _optionSelected.value = s
        //orderByName()
    }

    //private fun getListGames() = (_uiState.value as GameUiState.Success).data


    private fun onCreate() {
        viewModelScope.launch {
            getGamesUseCase().collect { state ->
                _uiState.value = state
            }
        }
    }

/*    fun orderByName() {
        viewModelScope.launch {
            val sortedList = getListGames().sortedBy { it.name }
            _uiState.update{GameUiState.Success(sortedList) }
        }
    }*/


}