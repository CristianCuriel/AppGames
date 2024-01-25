package com.prueba.appgames.app.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import okio.IOException
import retrofit2.HttpException


@Composable
fun ScreenErrorState(error: Throwable) {
    var httpCode = ""
    var errorBody = ""

    when (error) {
        is HttpException -> {
            httpCode = error.code().toString()
            errorBody = error.response()?.errorBody()?.string().toString()
        }

        is IOException ->{
            errorBody = error.message.toString()
            httpCode = error.cause?.toString() ?: "Error"
        }

        is Exception -> {
            errorBody = error.message.toString()
            httpCode = error.cause?.toString() ?: "Error"
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Warning, contentDescription = "Error", tint = Color.White, modifier = Modifier.size(50.dp).padding(4.dp))
            Text(
                text = httpCode,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Black,
                color = Color.White,
                textAlign = TextAlign.Center,
                letterSpacing = 2.sp,
                fontSize = 55.sp
            )
        }


        Text(
            text = errorBody,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }

}