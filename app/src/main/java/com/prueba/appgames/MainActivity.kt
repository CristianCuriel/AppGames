package com.prueba.appgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prueba.appgames.app.ui.viewmodel.GameViewModel
import com.prueba.appgames.app.ui.views.NavManager
import com.prueba.appgames.app.ui.views.component.Modal
import com.prueba.appgames.ui.theme.AppGamesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGamesTheme {
                // A surface container using the 'background' color from the theme

                val listState = rememberLazyListState()
                val coroutineScope = rememberCoroutineScope()

                Scaffold(
                    containerColor = Color(0xFF141414),
                    modifier = Modifier.fillMaxSize(),
                    topBar = { MyTopAppBard() },
                    floatingActionButton ={MyfloatingButton(GameViewModel(), listState, coroutineScope)}

                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Myheader(GameViewModel())
                        NavManager(GameViewModel(), listState, coroutineScope)
                    }

                }
            }
        }
    }

}

@Composable
fun MyfloatingButton(
    gameViewModel: GameViewModel,
    listState: LazyListState,
    coroutineScope: CoroutineScope
) {

    val showButton by gameViewModel.showButton.collectAsState()

    if (showButton) {
        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                    gameViewModel.updateButtonVisibility(false)
                }
            }
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Ir al inicio")
        }
    }
}

@Composable
fun Myheader(gameViewModel: GameViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {

        Text(
            text = "Todos los juegos",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OrderByPopularity(gameViewModel)

    }
}

@Composable
fun OrderByPopularity(gameViewModel: GameViewModel) {
    val showDialog: Boolean by gameViewModel.showDialog.collectAsState()
    val onOrder: String by gameViewModel.optionSelected.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF252525), shape = RoundedCornerShape(12.dp))
                    .padding(8.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier.clickable {
                    gameViewModel.onshowDialog()
                }) {
                    Text(text = "Order by:", color = Color.White, fontSize = 14.sp)
                    Text(
                        text = onOrder.ifEmpty { "Seleccionar" },
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(horizontal = 2.dp),
                        fontSize = 14.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "abajo",
                        tint = Color.White
                    )
                }

            }
        }

        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF252525), shape = RoundedCornerShape(12.dp))
                    .padding(8.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row() {
                    Text(text = "Plataformas", color = Color.White, fontSize = 14.sp)
                    Text(
                        text = "",
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = 14.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "abajo",
                        tint = Color.White
                    )
                }

            }
        }

    }//Row

    Modal(show = showDialog, onDismiss = { gameViewModel.onshowDialog() }, gameViewModel)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBard() {

    TopAppBar(
        navigationIcon = {
            Text(
                text = "RAWG",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                textAlign = TextAlign.Center,
                letterSpacing = 6.sp,
                modifier = Modifier.padding(end = 8.dp)

            )
        }, title = {
            Search()
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF151515)),
        actions = {

            // Icono de perfil (con imagen de ejemplo)
            Image(
                painter = painterResource(id = R.drawable.user), // Asegúrate de tener un recurso drawable para el icono del perfil
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .padding(horizontal = 4.dp)
                    .clip(CircleShape) // Para hacer la imagen redonda
            )

            Icon(
                imageVector = Icons.Filled.Menu,
                tint = Color.White,
                modifier = Modifier.size(42.dp, 42.dp),
                contentDescription = "Localized description"
            )
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Search() {

    // Campo de búsqueda
    var searchText by remember { mutableStateOf("") }
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // Maneja el back press para ocultar el teclado y limpiar el foco
    BackHandler(enabled = isTextFieldFocused) {
        keyboardController?.hide()
        focusManager.clearFocus()
        isTextFieldFocused = false
    }

    Box(
        modifier = Modifier
            .height(45.dp) // Altura del TextField
            .width(195.dp)
            .padding(start = 12.dp, end = 4.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = { newText -> searchText = newText },
            textStyle = TextStyle.Default.copy(fontSize = 12.sp, color = Color.DarkGray),
            placeholder = {
                Text(
                    text = "Buscar",
                    color = if (isTextFieldFocused || searchText.isNotEmpty()) Color.White else Color.Gray,
                    fontSize = 10.sp
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = if (isTextFieldFocused || searchText.isNotEmpty()) Color.White else Color(
                    0xFF3A3A3A
                ),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(40.dp), // Esquinas redondeadas del TextField
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = if (isTextFieldFocused || searchText.isNotEmpty()) Color(0xFF3A3A3A) else Color.White
                )
            },
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),

            modifier = Modifier
                .fillMaxSize()
                .onFocusChanged { focusState ->
                    isTextFieldFocused = focusState.isFocused
                }
        )
    }
}
