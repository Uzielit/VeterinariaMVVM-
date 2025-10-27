package com.veterinaria.ui.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Link(
    text: String,
    fontSize: Int = 15,
    color: Color = Color.Blue,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    onClick: () -> Unit // Acción al hacer click
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        color = color,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodyMedium.copy(
            textDecoration = TextDecoration.Underline // Subrayado para parecer un link
        ),
        modifier = modifier
            .clickable { onClick() }
            .padding(2.dp) // Padding opcional para mejor área clickeable
    )
}