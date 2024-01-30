package com.prueba.appgames.app.ui.viewmodel

import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.appgames.app.data.Models.infoGame.PlatformXX
import com.prueba.appgames.app.data.Models.infoGame.Rating
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.domain.GameUseCase
import com.prueba.appgames.app.ui.states.GameUiState
import com.prueba.appgames.app.ui.states.InfoGameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

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

    private val _uiStateGameInfo = MutableStateFlow<InfoGameUiState>(InfoGameUiState.Loading)
    val uiStateGameInfo: StateFlow<InfoGameUiState> = _uiStateGameInfo

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var nextPage = 1


    init {
        if (_gamesList.value.isEmpty()) {
            loadMoreGames()
        }
    }

    fun loadMoreGames() {

        if (_isLoading.value) return

        // Indicamos que estamos comenzando una carga
        _isLoading.value = true

        viewModelScope.launch {
            getGamesUseCase.getMoreGamesCaseUse(nextPage).collect { state ->

                if (state is GameUiState.Success) {
                    val currentGames = _gamesList.value.toMutableList()
                    val r = state.data.listGames
                    currentGames.addAll(r)
                    _gamesList.update { currentGames }
                    nextPage = calculateNextPage(state.data.next) ?: nextPage
                }
                _uiState.value = state
            }
        }

        _isLoading.value = false

    }

    fun onInfoGame(idGame: Int) {
        viewModelScope.launch {
            getGamesUseCase.getInfoGameCaseUse(idGame).collect { state ->
                _uiStateGameInfo.value = state
            }
        }
    }


    fun onshowDialog() {
        _showDialog.value = !_showDialog.value
    }

    fun selectedFilter(s: String) {
        _optionSelected.value = s
        when (s){
             "Nombre" -> { orderByName() }
        }

    }

       fun orderByName() {
            viewModelScope.launch {
                val currentGames = _gamesList.value.toMutableList()
                val sortedList = currentGames
                    .sortedBy { it.name }
                _gamesList.update{sortedList}
            }
        }

    private fun calculateNextPage(nextUrl: String?): Int? {
        return nextUrl?.let {
            val uri = Uri.parse(it)
            val pageQuery = uri.getQueryParameter("page")
            pageQuery?.toIntOrNull()
        }
    }

    fun getColorForRating(ratingId: Int): Color {
        return when (ratingId) {
            5 -> Color(0xff77BD37) // Exceptional
            4 -> Color(0xff567CE1) // Recommended
            3 -> Color(0xffF9B44A) // Meh
            1 -> Color(0xffF93948) // Skip
            else -> Color.Gray // Por si hay otros valores inesperados
        }
    }

    fun categoriaRating(ratings: List<Rating>): String {
        var a = 0.0
        var s = ""
        for (rating in ratings) {
            if (rating.percent > a) {
                a = rating.percent
                s = rating.title
            }
        }
        return s
    }


    fun formatDateString(originalDateString: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val targetFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        val date = originalFormat.parse(originalDateString)
        return targetFormat.format(date!!).uppercase(Locale.ROOT)
    }

    fun concatenateTitles(platforms: List<PlatformXX>): String {
        return platforms.joinToString(separator = ", ") { it.platform.name }
    }


}