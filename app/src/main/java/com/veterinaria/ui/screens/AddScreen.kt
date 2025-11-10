package com.veterinaria.ui.screens // El paquete que indicaste

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoRegistroScreen(navController: NavController) {

    // Estados para los campos del formulario
    var nombre by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    // --- Estado para el dropdown de Vacunas ---
    var vacunasExpanded by remember { mutableStateOf(false) }
    var vacunasSelected by remember { mutableStateOf("") }
    // Opciones de ejemplo, puedes cambiarlas
    val vacunasOptions = listOf("Sí, todas", "No, ninguna", "Algunas pendientes")

    // --- Estado para el dropdown de Especie ---
    var especieExpanded by remember { mutableStateOf(false) }
    var especieSelected by remember { mutableStateOf("") }
    // Opciones de ejemplo, puedes cambiarlas
    val especieOptions = listOf("Perro", "Gato", "Ave", "Reptil", "Otro")

    Scaffold(
        topBar = {
            TopAppBar(
                // Título de la barra superior según el wireframe
                title = { Text("Nombre usuario") },
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
                .padding(horizontal = 30.dp, vertical = 10.dp) // Padding del diseño
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Título principal dentro del contenido
            Text(
                text = "NUEVO REGISTRO",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.Start) // Alineado a la izquierda como en el wireframe
                    .padding(vertical = 16.dp)
            )

            // Campo de texto para Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre(Mascota)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para Raza
            OutlinedTextField(
                value = raza,
                onValueChange = { raza = it },
                label = { Text("Raza") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para Fecha (con ícono)
            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (Nacimiento)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("DD/MM/AAAA") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- Dropdown para Vacunas ---
            ExposedDropdownMenuBox(
                expanded = vacunasExpanded,
                onExpandedChange = { vacunasExpanded = !vacunasExpanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = vacunasSelected,
                    onValueChange = {}, // No se puede editar manualmente
                    readOnly = true,
                    label = { Text("Vacunas (que han aplicado)") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = vacunasExpanded)
                    },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = vacunasExpanded,
                    onDismissRequest = { vacunasExpanded = false }
                ) {
                    vacunasOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                vacunasSelected = option
                                vacunasExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Dropdown para Especie ---
            ExposedDropdownMenuBox(
                expanded = especieExpanded,
                onExpandedChange = { especieExpanded = !especieExpanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = especieSelected,
                    onValueChange = {}, // No se puede editar manualmente
                    readOnly = true,
                    label = { Text("Especie") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = especieExpanded)
                    },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = especieExpanded,
                    onDismissRequest = { especieExpanded = false }
                ) {
                    especieOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                especieSelected = option
                                especieExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Placeholder para la Foto ---
            Text(
                text = "Foto",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) // Altura de ejemplo para el placeholder
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                // El 'X' en el wireframe representa un placeholder.
                // Usamos un ícono de cámara que es más común en la práctica.
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "Añadir foto",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // --- Botones de Aceptar y Cancelar ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // Padding para que no peguen a los bordes
                horizontalArrangement = Arrangement.SpaceAround // Espacio entre botones
            ) {
                // Botón de Aceptar
                Button(
                    onClick = {
                        // Lógica para guardar el nuevo registro...
                        navController.popBackStack() // Regresar
                    },
                    modifier = Modifier.weight(1f) // Ocupa la mitad del espacio
                ) {
                    Text("Aceptar")
                }

                Spacer(modifier = Modifier.width(16.dp)) // Espacio entre botones

                // Botón de Cancelar (usamos OutlinedButton para diferenciarlo)
                OutlinedButton(
                    onClick = {
                        navController.popBackStack() // Simplemente regresar
                    },
                    modifier = Modifier.weight(1f) // Ocupa la otra mitad
                ) {
                    Text("Cancelar")
                }
            }

            Spacer(modifier = Modifier.height(20.dp)) // Espacio al final
        }
    }
}

// --- ¡AQUÍ ESTÁ EL PREVIEW! ---
// Puedes ver el diseño en la pestaña "Split" o "Design" de Android Studio.
@Preview(showBackground = true)
@Composable
fun NuevoRegistroScreenPreview() {
    // Usamos un NavController de mentira para que el preview funcione
    NuevoRegistroScreen(navController = rememberNavController())
} //segundo cambio