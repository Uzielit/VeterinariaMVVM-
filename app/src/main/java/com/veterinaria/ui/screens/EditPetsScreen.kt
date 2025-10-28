package com.veterinaria.ui.screens // Paquete que indicaste

// Importaciones necesarias
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.veterinaria.viewmodel.EditPetsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPetsScreen (editPets: EditPetsViewModel, navController: NavController){

    //cambios
    // En una app real, estos valores vendrían de la mascota que estás editando.
    var petName by remember { mutableStateOf("Nombre Actual") }
    var breed by remember { mutableStateOf("Raza Actual") }
    var birthDate by remember { mutableStateOf("10/10/2020") }
    var vaccines by remember { mutableStateOf("Registro de vacunas actual") }
    var species by remember { mutableStateOf("Especie Actual") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar") }, // Título "Editar" del wireframe
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // Placeholder para la Foto (ahora está arriba)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f) // Hacemos la caja un poco más pequeña
                    .aspectRatio(1f) // Mantenemos la proporción cuadrada
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "Cambiar foto",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Campo de texto para Cambiar Nombre
            OutlinedTextField(
                value = petName,
                onValueChange = { petName = it },
                label = { Text("Cambiar Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para Cambiar Raza
            OutlinedTextField(
                value = breed,
                onValueChange = { breed = it },
                label = { Text("Cambiar Raza") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para Fecha (con ícono de calendario)
            OutlinedTextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = { Text("Cambiar Fecha de Nacimiento") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("DD/MM/AAAA") },
                trailingIcon = { // Ícono de calendario
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para Cambiar Registro de Vacunas
            OutlinedTextField(
                value = vaccines,
                onValueChange = { vaccines = it },
                label = { Text("Cambiar Registro de Vacunas") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para Cambiar Especie
            OutlinedTextField(
                value = species,
                onValueChange = { species = it },
                label = { Text("Cambiar Especie") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Botón de Confirmar (solo hay uno)
            Button(
                onClick = {
                    // Lógica para guardar la edición...
                    navController.popBackStack() // Regresar después de confirmar
                },
                modifier = Modifier.fillMaxWidth(0.8f) // Hacemos el botón un poco más pequeño
            ) {
                Text("Confirmar")
            }

            Spacer(modifier = Modifier.height(20.dp)) // Espacio al final
        }
    }
}