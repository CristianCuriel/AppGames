package com.prueba.appgames.app.ui.viewmodel

import android.net.ConnectivityManager
import android.net.Network
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.domain.GameUseCase
import com.prueba.appgames.app.ui.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel() : ViewModel() {

    var getGamesUseCase = GameUseCase()

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private val _optionSelected = MutableStateFlow("")
    val optionSelected: StateFlow<String> = _optionSelected

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Loading)
    val uiState: StateFlow<GameUiState> = _uiState

    private val _gamesList = MutableStateFlow<List<listGamesModel>>(emptyList())
    val gamesList: StateFlow<List<listGamesModel>> = _gamesList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showButton = MutableStateFlow(false)
    val showButton: StateFlow<Boolean> = _showButton

    // Flow para manejar el estado de la conexión de red
    private val _isNetworkAvailable = MutableStateFlow(false)
    val isNetworkAvailable:StateFlow<Boolean> = _isNetworkAvailable


    init {
        loadMoreGames()
    }

    fun updateButtonVisibility(show: Boolean) {
        _showButton.value = show
    }

    private var nextPage = 1

    fun loadMoreGames() {

        if (_isLoading.value) return

        // Indicamos que estamos comenzando una carga
        _isLoading.value = true

        viewModelScope.launch {
            getGamesUseCase.getMoreGamesCaseUse(nextPage).collect { state ->

                if (state is GameUiState.Success){
                    val currentGames = _gamesList.value.toMutableList()
                    val R = state.data.listGames
                    currentGames.addAll(R)
                    _gamesList.update { currentGames }
                    nextPage = calculateNextPage(state.data.next) ?: nextPage
                }
                _uiState.value = state
            }
        }

        _isLoading.value = false

    }

    fun onshowDialog() {
        _showDialog.value = !_showDialog.value
    }

    fun selectedFilter(s: String) {
        _optionSelected.value = s
        //orderByName()
    }

    // NetworkCallback para escuchar cambios en la conectividad
    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isNetworkAvailable.value = true
        }

        override fun onLost(network: Network) {
            _isNetworkAvailable.value = false
        }
    }

/*
    private fun onCreate() {
        viewModelScope.launch {
            getGamesUseCase().collect { state ->
                _uiState.value = state
                _gamesList.value = (_uiState.value as GameUiState.Success).data.listGames
            }
        }
    }*/

    /*    fun orderByName() {
            viewModelScope.launch {
                val sortedList = getListGames().sortedBy { it.name }
                _uiState.update{GameUiState.Success(sortedList) }
            }
        }*/

       private fun calculateNextPage(nextUrl: String?): Int? {
           return nextUrl?.let {
               val uri = Uri.parse(it)
               val pageQuery = uri.getQueryParameter("page")
               pageQuery?.toIntOrNull()
           }

       }


}