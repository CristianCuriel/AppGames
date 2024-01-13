package com.prueba.appgames.app.ui.views

import GameCard
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.ui.GameUiState
import com.prueba.appgames.app.ui.viewmodel.GameViewModel

@Composable
fun NavManager(viewModel: GameViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current


    when(uiState){

        is GameUiState.Error -> {
            Toast.makeText(context,"${(uiState as GameUiState.Error).exception}", LENGTH_SHORT).show()
        }

        GameUiState.Loading -> {
            CircularProgressIndicator()
        }

        is GameUiState.Success -> {
            val games = (uiState as GameUiState.Success).data
            ItemsGamesView(games)
        }
    }

}// NavManager

@Composable
fun ItemsGamesView( games: List<listGamesModel>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(games) { game ->
            GameCard(game)
        }
    }
} //ItemsGamesView
