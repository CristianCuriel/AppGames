
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.prueba.appgames.R
import com.prueba.appgames.app.data.Models.ParentPlatform
import com.prueba.appgames.app.data.Models.ShortScreenshot
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.ui.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    game: listGamesModel,
    viewModel: GameViewModel,
) {


    ElevatedCard(
        modifier = Modifier

            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF202020)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            ImageGames(game)

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                PlataformasGames(game.metacritic, game.parent_platforms)

                GamesTitles(game.name)

                BotonActionGames(viewModel)

                BotonViewMore(game)

            }//Column

        }

    }
}


@Composable
fun previewMore(game: listGamesModel) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Row(
                modifier = Modifier.weight(1f).padding(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Fecha de lazamiento", color = Color(0xFF6F6F6F), fontSize = 12.sp)
            }

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(game.released, color = Color(0xFFE1E1E1), fontSize = 12.sp)
            }
        }

        Divider(
            Modifier
                .background(Color(0xFF6F6F6F))
                .height((0.8).dp)
                .fillMaxWidth()
        )

        Row(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Row(
                modifier = Modifier.weight(1f).padding(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Generos", color = Color(0xFF6F6F6F), fontSize = 12.sp)
            }

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(game.genres.joinToString(separator = ", "){it.name}, color = Color(0xFFE1E1E1), fontSize = 12.sp)
            }
        }

        Divider(
            Modifier
                .background(Color(0xFF6F6F6F))
                .height((0.8).dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ImageGames(game: listGamesModel) {

    var currentIndex by rememberSaveable { mutableIntStateOf(0) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(210.dp)
        .clickable {
            currentIndex = (currentIndex + 1) % game.short_screenshots.size
        }) {

        LazyRow(Modifier.fillMaxSize()) {
            itemsIndexed(game.short_screenshots) { index, imageUrl ->
                if (index == currentIndex) {
                    var painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl.image)
                            .crossfade(true)
                            .size(Size.ORIGINAL) // Set the target size to load the image at.
                            .build()
                    )

                    if (painter.state is AsyncImagePainter.State.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .width(64.dp),
                            color = Color.White
                        )
                    } else {
                        Image(
                            painter = painter,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(210.dp)
                                .size(390.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }// If del index
            }
        }// LazyRow
    }// Box
    indicadorImagen(currentIndex, game.short_screenshots)
}// ImageGames

@Composable
fun indicadorImagen(currentIndex: Int, shortScreenshots: List<ShortScreenshot>) {
    // Indicadores de posición
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        shortScreenshots.forEachIndexed { index, _ ->
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 4.dp)
                    .background(
                        color = if (index == currentIndex) Color.White else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun GamesTitles(gameTitle: String) {
    Text(
        text = gameTitle,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        color = Color.White
    )
}

@Composable
fun BotonViewMore(game: listGamesModel) {
    var show by rememberSaveable { mutableStateOf(false) }

    if(show){
        previewMore(game)
    }

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(onClick = { show =!show }) {
            Text(
                text = if (show) "Ver menos" else "Ver mas" ,
                textDecoration = TextDecoration.Underline,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}


@Composable
fun PlataformasGames(
    metacritic: Int,
    parentPlatforms: List<ParentPlatform>,
) {

    val metacriticColor = if (metacritic > 73) Color(0xFF60AE42) else Color(0xFFFDCA52)

    val platformIconMap = mapOf(
        "pc" to R.drawable.logowindows,
        "playstation" to R.drawable.logoplaystation,
        "xbox" to R.drawable.logoxbox,
        "mac" to R.drawable.logomac,
        "nintendo" to R.drawable.nintendo
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.weight(2f)
        ) {

            parentPlatforms.forEach { P ->
                Log.i("Cris", P.platform.slug)
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .size(width = 32.dp, height = 28.dp)
                    .border(
                        width = (1 / 2).dp,
                        color = metacriticColor,
                        shape = RoundedCornerShape(3.dp)
                    )


            ) {
                Text(
                    text = "$metacritic",
                    fontWeight = FontWeight.ExtraBold,
                    color = metacriticColor
                )
            }
        }

    }//Row
}

@Composable
fun BotonFavorite(
    isSelected: Boolean,
    onItemSelected: () -> Unit,
) {

    var cardColor by remember { mutableStateOf(Color(0xFFF373737)) }
    var defaultValue = 0

    if (isSelected) {
        cardColor = Color(0xFFF55A229)
        defaultValue += 1
    } else {
        cardColor = Color(0xFFF373737)
        defaultValue = 0
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = Modifier
            .padding(vertical = 6.dp)
            .size(width = 54.dp, height = 34.dp)
            .clickable { onItemSelected() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "", modifier = Modifier
                    .size(30.dp),
                tint = Color.White
            )

            Text(
                text = "$defaultValue",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold

            )
        }
    }// Card

}


@Composable
fun BotonMore() {

    var cardColor by remember { mutableStateOf(Color(0xFFF373737)) }

    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .size(width = 44.dp, height = 34.dp)
            .clickable {
                cardColor =
                    if (cardColor == Color(0xFFF373737)) Color(0xFF55A229) else Color(0xFFF373737)
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Icon(
                Icons.Rounded.MoreVert,
                contentDescription = "", modifier = Modifier
                    .size(26.dp)
                    .graphicsLayer(rotationZ = 90f),
                tint = Color.White
            )
        }

    }
}

@Composable
fun BotonActionGames(viewModel: GameViewModel) {

    var like by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        BotonFavorite(isSelected = like, onItemSelected = {
            like = !like

        })
        BotonMore()


    }//Row
}
