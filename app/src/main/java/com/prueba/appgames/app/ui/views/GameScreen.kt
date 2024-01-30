package com.prueba.appgames.app.ui.views

import GameCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.ui.states.GameUiState
import com.prueba.appgames.app.ui.viewmodel.GameViewModel
import com.prueba.appgames.app.ui.views.component.Myheader

@Composable
fun NavManager(viewModel: GameViewModel, navigationController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()
    val gamesList by viewModel.gamesList.collectAsState()
    //val context = LocalContext.current

    when (uiState) {

        is GameUiState.Error -> {
            ScreenErrorState((uiState as GameUiState.Error).exception)
        }

        GameUiState.Loading -> {
            CircularProgressIndicator()
        }

        is GameUiState.Success -> {
            ItemsGamesView(gamesList, viewModel, navigationController)
        }
    }

}// NavManager

@Composable
fun ItemsGamesView(
    games: List<listGamesModel>,
    viewModel: GameViewModel,
    navigationController: NavHostController,
) {

    val isLoading by viewModel.isLoading.collectAsState()
    val listState = rememberLazyListState()
    Column(Modifier.fillMaxWidth()) {
        Myheader(GameViewModel())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            itemsIndexed(games) { index, game ->
                GameCard(game, navigationController)

                if (index == games.size - 1 && !isLoading) {
                    LoadingMore()
                    viewModel.loadMoreGames()
                }


            }
        }
    }



} //ItemsGamesView

@Preview
@Composable
fun LoadingMore() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), Alignment.TopCenter
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
