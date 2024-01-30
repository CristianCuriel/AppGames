package com.prueba.appgames.app.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.prueba.appgames.R
import com.prueba.appgames.app.data.Models.infoGame.ParentPlatform
import com.prueba.appgames.app.data.Models.infoGame.PlatformXX
import com.prueba.appgames.app.data.Models.infoGame.Rating
import com.prueba.appgames.app.data.network.Response.GameInfoResponse
import com.prueba.appgames.app.ui.states.InfoGameUiState
import com.prueba.appgames.app.ui.viewmodel.GameViewModel
import com.prueba.appgames.app.ui.views.component.MetaScore

@Composable
fun MyScreen2(viewModel: GameViewModel, idGame: Int) {
    viewModel.onInfoGame(idGame)

    val uiStateGameInfo by viewModel.uiStateGameInfo.collectAsState()

    when (uiStateGameInfo) {

        is InfoGameUiState.Error -> {
            ScreenErrorState((uiStateGameInfo as InfoGameUiState.Error).exception)
        }

        InfoGameUiState.Loading -> {
            CircularProgressIndicator()
        }

        is InfoGameUiState.Success -> {
            InfoGame((uiStateGameInfo as InfoGameUiState.Success).data, viewModel)
        }
    }


}

@Composable
fun InfoGame(data: GameInfoResponse, viewModel: GameViewModel) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(
                rememberScrollState()
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            ImageGame(data.background_image, h = 210.dp, s = 390.dp, 0.4f)
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Fecha y duración promedio

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.White)
                    )
                    {
                        Text(
                            viewModel.formatDateString(data.released),
                            color = Color.Black,
                            style = MaterialTheme.typography.displaySmall,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    PlataformasGames(data.parent_platforms)

                }

                Text(
                    "AVERAGE PLAYTIME: ${data.playtime} HOURS",
                    color = Color.White,
                    style = MaterialTheme.typography.displaySmall,
                    letterSpacing = 4.sp,
                    fontSize = 12.sp
                )

                // Título del juego
                Text(
                    data.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 12.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            ImageGame(data.background_image_additional, h = 200.dp, s = 0.dp, 1f)
        }


        Text(
            "Ultima modificacion: ${viewModel.formatDateString(data.updated)} ",
            color = Color.DarkGray,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            viewModel.categoriaRating(data.ratings),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 24.sp
        )

        Text(
            "${data.rating} RATINGS",
            color = Color.DarkGray,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 14.sp,
            textDecoration = TextDecoration.Underline,
            letterSpacing = 4.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        RatingBar(data.ratings, viewModel)

        Spacer(modifier = Modifier.height(14.dp))

        RatingData(data.ratings, viewModel)

        Spacer(modifier = Modifier.height(20.dp))

        GetAbout(data.description)

        Spacer(modifier = Modifier.height(14.dp))

        BodyPlatformsInfo(data.platforms, data, viewModel)


    } //Column

}// InfoGame

@Composable
fun BodyPlatformsInfo(
    platforms: List<PlatformXX>,
    data: GameInfoResponse,
    viewModel: GameViewModel,
) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Column(
                Modifier
                    .padding(end = 4.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "Plataformas",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = viewModel.concatenateTitles(platforms),
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Text(
                    text = "Genero",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                data.genres.forEach {
                    Text(
                        text = it.name,
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Metascore",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                MetaScore(data.metacritic)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Fecha de lanzamiento",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = viewModel.formatDateString(data.released),
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

            }
        }
    }
}

@Composable
fun GetAbout(description: String) {
    Column(
        Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "About",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = description,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun RatingData(ratings: List<Rating>, viewModel: GameViewModel) {

    // Asignar los ratings a dos listas, una para la fila superior y otra para la fila inferior.
    val (topRatings, bottomRatings) = ratings.withIndex().partition { it.index < 2 }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Fila superior
        Row(modifier = Modifier.fillMaxWidth()) {
            topRatings.forEach { indexedRating ->
                RowRating(
                    viewModel,
                    title = indexedRating.value.title,
                    count = indexedRating.value.count,
                    id = indexedRating.value.id
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Fila inferior
        Row(modifier = Modifier.fillMaxWidth()) {
            bottomRatings.forEach { indexedRating ->
                RowRating(
                    viewModel,
                    title = indexedRating.value.title,
                    count = indexedRating.value.count,
                    id = indexedRating.value.id
                )
            }
        }
    }
}

@Composable
fun RowRating(viewModel: GameViewModel, title: String, count: Int, id: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(viewModel.getColorForRating(id))
        )
        Text(
            title,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(text = "$count", fontSize = 14.sp, color = Color.DarkGray)
    }
}

@Composable
fun RatingBar(ratings: List<Rating>, viewModel: GameViewModel) {
    // Asume que la lista 'ratings' está ordenada de acuerdo a la clasificación deseada
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        for (rating in ratings) {
            Box(
                modifier = Modifier
                    .weight(rating.percent.toFloat())
                    .fillMaxHeight()
                    .background(color = viewModel.getColorForRating(rating.id)) // Función personalizada para determinar el color
            )
        }
    }
}


@Composable
fun PlataformasGames(
    parentPlatforms: List<ParentPlatform>,
) {

    val platformIconMap = mapOf(
        "pc" to R.drawable.logowindows,
        "playstation" to R.drawable.logoplaystation,
        "xbox" to R.drawable.logoxbox,
        "mac" to R.drawable.logomac,
        "nintendo" to R.drawable.nintendo
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {

        parentPlatforms.forEach { P ->
            platformIconMap[P.platform.slug]?.let { iconResId ->
                Icon(
                    painterResource(id = iconResId),
                    contentDescription = "", modifier = Modifier
                        .size(26.dp)
                        .padding(end = 8.dp),
                    tint = Color(0xFFFFFFFF)
                )
            }
        }

    }//Row
}

@Composable
fun ImageGame(backgroundImage: String, h: Dp, s: Dp, alpha: Float) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(backgroundImage)
            .crossfade(true)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    Image(
        painter = painter,
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(h)
            .size(s),
        alpha = alpha,
        //colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.7f)),
        contentScale = ContentScale.Crop
    )
}

