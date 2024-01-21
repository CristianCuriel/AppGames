package com.prueba.appgames.app.ui.views.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prueba.appgames.app.ui.viewmodel.GameViewModel

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