package com.veterinaria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.veterinaria.data.model.Mascota
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun PetCard(mascota: Mascota, onClick: () -> Unit,modifier: Modifier = Modifier) {
    val petBgColor = Color(0xFFF5F8F0)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable{onClick()},
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = petBgColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = mascota.imageUrl,
                contentDescription = "Foto de ${mascota.nombre}",
                modifier = Modifier
                    .size(70.dp)
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column (  modifier = Modifier.weight(0.6f)){
                Text(text = "Nombre: ${mascota.nombre}", fontWeight = FontWeight.Bold)
                Text(text = "Especie: ${mascota.especie}")
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                val date = Date(mascota.fechaNacimiento)

                val fechaFormateada = formatter.format(date)
                Text(text = "Nacio: $fechaFormateada")
            }
        }
    }
}

