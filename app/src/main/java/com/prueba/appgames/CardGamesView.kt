import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    backgroundImage: String,
    gameTitle: String,
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(backgroundImage)
            .crossfade(true)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    ElevatedCard(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF202020)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            if (painter.state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                        .width(64.dp),
                    color = Color.White
                )
            } else {
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                PlataformasGames()

                GamesTitles(gameTitle)

                BotonActionGames()

                BotonViewMore()


            }//Column

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
fun BotonViewMore() {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Ver mas",
                textDecoration = TextDecoration.Underline,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun PlataformasGames() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Icon(
            painterResource(id = R.drawable.logowindows),
            contentDescription = "", modifier = Modifier
                .size(26.dp)
                .padding(end = 8.dp),
            tint = Color(0xFFFFFFFF)
        )
        Icon(
            painterResource(id = R.drawable.logoplaystation),
            contentDescription = "", modifier = Modifier
                .size(26.dp)
                .padding(end = 8.dp),
            tint = Color(0xFFFFFFFF)
        )
        Icon(
            painterResource(id = R.drawable.logoxbox),
            contentDescription = "", modifier = Modifier
                .size(26.dp)
                .padding(end = 8.dp),
            tint = Color(0xFFFFFFFF)
        )
    }//Row
}

@Composable
fun BotonActionGames() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF373737)),
            modifier = Modifier
                .padding(vertical = 6.dp)
                .size(width = 54.dp, height = 34.dp),
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
                    text = "0",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold

                )
            }
        }// Card

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF373737)),
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .size(width = 44.dp, height = 34.dp),
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

    }//Row
}
