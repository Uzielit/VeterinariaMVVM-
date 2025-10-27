package com.veterinaria.ui.components.images

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Image(imageRes: Int, size: Int = 100) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Imagen circular",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size.dp)
            .border(border = BorderStroke(1.dp, Color.Black), shape = CircleShape)
    )
}
