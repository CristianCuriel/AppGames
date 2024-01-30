package com.prueba.appgames.app.ui.views.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MetaScore(metacritic: Int){

    val metacriticColor = if (metacritic > 73) Color(0xFF60AE42) else Color(0xFFFDCA52)

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


