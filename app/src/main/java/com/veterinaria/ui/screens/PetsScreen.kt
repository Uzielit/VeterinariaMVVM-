package com.veterinaria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.veterinaria.R


data class Mascota(
    val nombre: String,
    val raza: String,
    val edad: String,
    val imagen: Int
)

@Composable
fun PetsScreen(viewModel: Any?, navController: NavController) { // viewModel no se usa aún
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Mis mascotas",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "Nombre Usuario",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = "Mascotas Registradas:",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))


            val mascotas = listOf(
                Mascota("Firulais", "Labrador", "2 años", R.drawable.firulais),
                Mascota("Michi", "Siames", "3 años", R.drawable.michi),
                Mascota("Rex", "Pastor Alemán", "1 año", R.drawable.rex)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(mascotas) { mascota ->
                    PetCard(mascota)
                }
            }
        }


        FloatingActionButton(
            onClick = { navController.navigate("add_pet") },
            containerColor = Color(0xFF2196F3),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar mascota"
            )
        }
    }
}

@Composable
fun PetCard(mascota: Mascota) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen local desde drawable
            Image(
                painter = painterResource(id = mascota.imagen),
                contentDescription = "Foto de ${mascota.nombre}",
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = "Nombre: ${mascota.nombre}", fontWeight = FontWeight.Bold)
                Text(text = "Raza: ${mascota.raza}")
                Text(text = "Edad: ${mascota.edad}")
            }
        }
    }
}
