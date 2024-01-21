package com.prueba.appgames.app.ui.views.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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