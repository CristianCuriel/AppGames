package com.prueba.appgames.app.ui.views

import GameCard
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.ui.GameUiState
import com.prueba.appgames.app.ui.viewmodel.GameViewModel

@Composable
fun NavManager(viewModel: GameViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val gamesList by viewModel.gamesList.collectAsState()
    val context = LocalContext.current


    when (uiState) {

        is GameUiState.Error -> {
            Toast.makeText(context, "${(uiState as GameUiState.Error).exception}", LENGTH_SHORT)
                .show()
        }

        GameUiState.Loading -> {
            CircularProgressIndicator()
        }

        is GameUiState.Success -> {
            ItemsGamesView(gamesList, viewModel)
        }
    }

}// NavManager

@Composable
fun ItemsGamesView(games: List<listGamesModel>, viewModel: GameViewModel) {

    val isLoading by viewModel.isLoading.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(games) { index, game ->
            GameCard(game)

            if (index == games.size - 1 && !isLoading) {
                LoadingMore()
                viewModel.loadMoreGames()
                Log.i("Cris", "Llegamos abajo")
            }

        }
    }
} //ItemsGamesView

@Preview
@Composable
fun LoadingMore() {

    Box(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 8.dp), Alignment.TopCenter
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Cargando mas Juegos",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            CircularProgressIndicator(color = Color.White, strokeWidth = 6.dp)
        }

    }
}
